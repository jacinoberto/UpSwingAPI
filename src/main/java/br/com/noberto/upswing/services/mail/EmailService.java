package br.com.noberto.upswing.services.mail;

import br.com.noberto.upswing.email.EmailRequest;
import br.com.noberto.upswing.email.EmailSender;
import br.com.noberto.upswing.models.*;
import br.com.noberto.upswing.repositories.*;
import br.com.noberto.upswing.util.emails.CompanyEmailToSend;
import br.com.noberto.upswing.util.emails.EmailRecoverPassword;
import br.com.noberto.upswing.util.emails.JobOfferEmailToSend;
import br.com.noberto.upswing.util.emails.StudentEmailToSend;
import br.com.noberto.upswing.util.filters.FilterStudentsByContractTypeStrategy;
import br.com.noberto.upswing.util.verifications.AbstractCheckObject;
import br.com.noberto.upswing.util.verifications.CompanyCheck;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final JobOfferRepository jobOfferRepository;
    private final JobOfferEmailToSend jobOfferEmailToSend;
    private final CompanyEmailToSend companyEmailToSend;
    private final StudentEmailToSend studentEmailToSend;
    private final FilterStudentsByContractTypeStrategy filterStudentsByContractType;
    private final AbstractCheckObject companyCheck;
    private final EmailRecoverPassword emailRecoverPassword;
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public EmailService(StudentRepository studentRepository, JavaMailSender mailSender, CompanyRepository companyRepository,
                        JobOfferRepository jobOfferRepository, ZipCodeRepository zipCodeRepository, BusinessAreaRepository businessAreaRepository, CourseRepository courseRepository, ClassRepository classRepository, JobOfferEmailToSend jobOfferEmailToSend, CompanyEmailToSend companyEmailToSend, StudentEmailToSend studentEmailToSend, FilterStudentsByContractTypeStrategy filterStudentsByContractType, AbstractCheckObject companyCheck, EntityManager entityManager, EmailRecoverPassword emailRecoverPassword) {
        this.studentRepository = studentRepository;
        this.mailSender = mailSender;
        this.companyRepository = companyRepository;
        this.jobOfferRepository = jobOfferRepository;
        this.jobOfferEmailToSend = jobOfferEmailToSend;
        this.companyEmailToSend = companyEmailToSend;
        this.studentEmailToSend = studentEmailToSend;
        this.filterStudentsByContractType = filterStudentsByContractType;
        this.emailRecoverPassword = emailRecoverPassword;
        this.companyCheck = new CompanyCheck(zipCodeRepository, businessAreaRepository, studentRepository, classRepository, companyRepository, courseRepository, entityManager);
        this.entityManager = entityManager;
    }

    @Async("threadPoolTaskExecutor")
    public void welcomeEmail(Student studentData) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        Student student = studentRepository.findById(studentData.getId()).orElseThrow(() -> new EntityExistsException("ID informado para Aluno é invalido!"));

        helper.setTo(student.getAccount().getEmail());
        helper.setSubject("Upswing te dá as boas vindas!");
        helper.setText(studentEmailToSend.getWelcomeEmail(student), true);
        mailSender.send(message);
    }

    @Async("threadPoolTaskExecutor")
    @Transactional
    public void emailForJobApplication(JobOffer jobOffer) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        Company company = companyRepository.findById(jobOffer.getCompany().getId())
                .orElseThrow(() -> new EntityExistsException("ID informado para Empresa invalido!"));
        List<Student> students = filterStudentsByContractType.filterStudents(jobOffer);
        entityManager.flush();

        for (Student studentData : students) {
            Student student = studentRepository.getReferenceById(studentData.getId());

            helper.setTo(student.getAccount().getEmail());
            helper.setSubject("Temos uma nova oportunidade compativel com seu perfil!");
            helper.setText(studentEmailToSend.getAppliedVacancyEmail(student, company, jobOffer), true);
            mailSender.send(message);
        }
    }

    @Async("threadPoolTaskExecutor")
    public void emailForPendingProfile(Company companyData) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        Company company = companyRepository.findById(companyData.getId())
                .orElseThrow(() -> new EntityExistsException("ID informado para Empresa invalido!"));

        helper.setTo(company.getAccount().getEmail());
        helper.setSubject("Seu perrfil está pendente de aprovação!");
        helper.setText(companyEmailToSend.emailPending(company), true);
        mailSender.send(message);
    }

    @Async("threadPoolTaskExecutor")
    public void emailForApprovedProfile(Company companyData) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        Company company = companyRepository.findById(companyData.getId())
                .orElseThrow(() -> new EntityExistsException("ID informado para Empresa invalido!"));

        helper.setTo(company.getAccount().getEmail());
        helper.setSubject("Seu perfil foi aprovado!");
        helper.setText(companyEmailToSend.emailApproved(company), true);
        mailSender.send(message);
    }

    @Async("threadPoolTaskExecutor")
    @Transactional
    public void emailForNotApprovedProfile(Company companyData) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        Company company = companyRepository.findById(companyData.getId())
                .orElseThrow(() -> new EntityExistsException("ID informado para Empresa invalido!"));
        entityManager.flush();

        helper.setTo(company.getAccount().getEmail());
        helper.setSubject("Seu perfil não foi aprovado!");
        helper.setText(companyEmailToSend.emailNotApproved(company), true);
        mailSender.send(message);
    }

    @Async("threadPoolTaskExecutor")
    @Transactional
    public void emailForPendingVacancy(JobOffer jobOffer) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        Company company = companyRepository.findById(jobOffer.getCompany().getId())
                .orElseThrow(() -> new EntityExistsException("ID informado para Empresa é invalido!"));
        entityManager.flush();

        helper.setTo(company.getAccount().getEmail());
        helper.setSubject("Sua vaga está pendente de aprovação!");
        helper.setText(jobOfferEmailToSend.emailPending(jobOffer), true);
        mailSender.send(message);
    }

    @Async("threadPoolTaskExecutor")
    @Transactional
    public void emailForApprovedVacancy(JobOffer jobOfferData) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        Company company = companyRepository.findById(jobOfferData.getCompany().getId())
                .orElseThrow(() -> new EntityExistsException("ID informado para Empresa é invalido!"));
        JobOffer jobOffer = jobOfferRepository.findById(jobOfferData.getId())
                .orElseThrow(() -> new EntityExistsException("Vaga invalida"));
        entityManager.flush();

        helper.setTo(company.getAccount().getEmail());
        helper.setSubject("Sua vaga foi aprovada!");
        helper.setText(jobOfferEmailToSend.emailApproved(jobOffer), true);
        mailSender.send(message);
    }

    @Async("threadPoolTaskExecutor")
    @Transactional
    public void emailForNotApprovedVacancy(JobOffer jobOfferData) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        Company company = companyCheck.checkCompany(jobOfferData.getCompany().getId());
        JobOffer jobOffer = jobOfferRepository.findById(jobOfferData.getId())
                .orElseThrow(() -> new EntityExistsException("Vaga invalida"));
        entityManager.flush();

        helper.setTo(company.getAccount().getEmail());
        helper.setSubject("Sua não foi aprovada!");
        helper.setText(jobOfferEmailToSend.emailNotApproved(jobOffer), true);
        mailSender.send(message);
    }

    @Async("threadPoolTaskExecutor")
    public void emailRecoverPassword(String token, String email) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject("Recupere sua senha");
        helper.setText(emailRecoverPassword.emailRecoverPassword(token), true);
        mailSender.send(message);
    }
}
