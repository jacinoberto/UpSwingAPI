package br.com.noberto.upswing.services.mail;

import br.com.noberto.upswing.dtos.academic.CourseSelect;
import br.com.noberto.upswing.email.EmailRequest;
import br.com.noberto.upswing.email.EmailSender;
import br.com.noberto.upswing.enums.Contract;
import br.com.noberto.upswing.enums.Location;
import br.com.noberto.upswing.models.*;
import br.com.noberto.upswing.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final EmailRepository repository;
    private final StudentRepository studentRepository;
    private final AutoApplyRepository autoApplyRepository;
    private final CompanyRepository companyRepository;
    private final JobOfferRepository jobOfferRepository;
    private final VacancyAndCourseRepository vacancyAndCourseRepository;

    @Autowired
    public EmailService(EmailRepository repository, StudentRepository studentRepository, JavaMailSender mailSender,
                        AutoApplyRepository autoApplyRepository, CompanyRepository companyRepository,
                        JobOfferRepository jobOfferRepository, VacancyAndCourseRepository vacancyAndCourseRepository) {
        this.repository = repository;
        this.studentRepository = studentRepository;
        this.mailSender = mailSender;
        this.autoApplyRepository = autoApplyRepository;
        this.companyRepository = companyRepository;
        this.jobOfferRepository = jobOfferRepository;
        this.vacancyAndCourseRepository = vacancyAndCourseRepository;
    }

    public void emailBoasVindasAluno(Student studentData){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        Student student = studentRepository.getReferenceById(studentData.getId());
        EmailRequest emailRequest = getEmailRequest(student);

        mailMessage.setFrom(emailRequest.getEmailFrom());
        mailMessage.setTo(student.getAccount().getEmail());
        mailMessage.setSubject(emailRequest.getSubject());
        mailMessage.setText(emailRequest.getMessage());

        mailSender.send(mailMessage);
        repository.save(new EmailSender(emailRequest));
    }

    public void emailForJobApplication(UUID jobOfferId){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        List<Student> students = filterStudentsByAddressAndContract(jobOfferId);

        for (Student studentData : students){
            Student student = studentRepository.getReferenceById(studentData.getId());
            EmailRequest emailRequest = appliedVacancyEmail(student);

            mailMessage.setFrom(emailRequest.getEmailFrom());
            mailMessage.setTo(student.getAccount().getEmail());
            mailMessage.setSubject(emailRequest.getSubject());
            mailMessage.setText(emailRequest.getMessage());

            mailSender.send(mailMessage);
            repository.save(new EmailSender(emailRequest));
        }
    }

    private static EmailRequest getEmailRequest(Student student) {
        EmailRequest emailRequest = new EmailRequest();

        emailRequest.setEmailTo(student.getAccount().getEmail());
        emailRequest.setSubject("UpSwing te dá as boas vindas!");
        emailRequest.setMessage("Olá " + student.getAccount().getName() + ",\n" +
                "Seja bem-vindo(a) ao UpSwing. nossa missão é proporcionar a você uma experiência" +
                "excepcional. Estamos constantemente trabalhando para oferecer funcionalidades inovadoras" +
                "e um ambiente fácil de usar para atender às suas necessidades.");
        return emailRequest;
    }

    private static EmailRequest appliedVacancyEmail(Student student) {
        EmailRequest emailRequest = new EmailRequest();

        emailRequest.setEmailTo(student.getAccount().getEmail());
        emailRequest.setSubject("Uma nova vaga foi postada!");
        emailRequest.setMessage("Olá " + student.getAccount().getName() + ",\n" +
                "Foi ofertada uma vaga compativel com seu perfil");
        return emailRequest;
    }

    /*
        --- METHODS ---
     */

    //Retorna estudantes compativeis com os parâmetros definidos em sua configuração de auto aplicação de vagas
    public List<Student> filterStudentsByAddressAndContract(UUID jobOfferId){
        JobOffer jobOffer = jobOfferRepository.getReferenceById(jobOfferId);
        Company company = companyRepository.getReferenceById(jobOffer.getCompany().getId());
        AutoApply autoApply = new AutoApply();

        List<Course> courses = vacancyAndCourseRepository.findByCoursesExistsInVacancyAndCourse(jobOfferId);
        List<CourseSelect> courseSelects = courses.stream().map(CourseSelect::new).toList();

        //Retorna os alunos das turmas que tem relação com os cursos escolhidos durante a oferta da vaga
        List<Student> studentsInCourse = returnStudentsExistsInCourse(courseSelects);

        //Retorna todos os alunos que optaram por automatizar ofertas de vagas
        List<Student> studentAutoApply = studentRepository.findByAutoApplyTrue();

        //Retorna todos os alunos que tem o mesmo estado da empresa que ofertou a vaga
        List<Student> studentState = studentRepository.findByStateTrue(jobOffer.getCompany().getId());

        /*Filtra as listas 'studentAutoApply', 'studentState' e 'studentsInCourse' adicionando a lista 'students' apenas
         uma instancia de um objeto do tipo aluno*/
        List<Student> students = studentsInCourse.stream()
                .filter(studentA -> studentState.stream()
                        .anyMatch(studentA::equals) || studentAutoApply.stream().anyMatch(studentA::equals))
                .distinct()
                .toList();

        /*Percorre toda lista 'students' mantendo em 'studentsByAddress' apenas os alunos que retornam verdadeiro para as
        condicionais feitas*/
        List<Student> studentsByAddress = new ArrayList<>();
        for (Student x : students) {
            autoApply = autoApplyRepository.findByStudentPresentAutoApply(x.getId());

            if (autoApply.getOfferLocation() == Location.STATE){
                studentsByAddress.add(studentRepository.getReferenceById(x.getId()));
            }
            if (autoApply.getOfferLocation() == Location.CITY){
                Student student = studentRepository.getReferenceById(x.getId());
                if (Objects.equals(student.getAddress().getZipCode().getCity(), company.getAddress().getZipCode().getCity()))
                    studentsByAddress.add(student);
            }
            if (autoApply.getOfferLocation() == Location.AREA){
                Student student = studentRepository.getReferenceById(x.getId());
                if (Objects.equals(student.getAddress().getZipCode().getArea(), company.getAddress().getZipCode().getArea()))
                    studentsByAddress.add(student);
            }
        }

        /*Percorre toda lista 'studentsByAddress' mantendo em 'qualifiedStudents' apenas os alunos que retornam verdadeiro para as
        condicionais feitas*/
        List<Student> qualifiedStudents = new ArrayList<>();
        for (Student y : studentsByAddress){
            autoApply = autoApplyRepository.findByStudentPresentAutoApply(y.getId());

            if (autoApply.getContract() == Contract.BOTH){
                qualifiedStudents.add(studentRepository.getReferenceById(y.getId()));
            }
            if (autoApply.getContract() == Contract.FIXED && jobOffer.getContract() == Contract.FIXED){
                qualifiedStudents.add(studentRepository.getReferenceById(y.getId()));
            }
            if (autoApply.getContract() == Contract.INTERNSHIP && jobOffer.getContract() == Contract.INTERNSHIP){
                qualifiedStudents.add(studentRepository.getReferenceById(y.getId()));
            }
        }

        return qualifiedStudents;
    }

    //Retorna estudantes matriculados em turmas que tem relação com o curso informado
    private List<Student> returnStudentsExistsInCourse(List<CourseSelect> courseSelects){
        List<Student> students = new ArrayList<>();

        for (CourseSelect courseSelect : courseSelects){
            students = studentRepository.findByStudentsExistsInCourse(courseSelect.courseId());
        }

        return students;
    }
}
