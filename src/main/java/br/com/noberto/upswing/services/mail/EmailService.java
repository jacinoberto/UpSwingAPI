package br.com.noberto.upswing.services.mail;

import br.com.noberto.upswing.email.EmailRequest;
import br.com.noberto.upswing.email.EmailSender;
import br.com.noberto.upswing.models.*;
import br.com.noberto.upswing.repositories.*;
import br.com.noberto.upswing.util.emails.CompanyEmailToSendStrategy;
import br.com.noberto.upswing.util.emails.JobOfferEmailToSendStrategy;
import br.com.noberto.upswing.util.emails.StudentEmailToSend;
import br.com.noberto.upswing.util.filters.FilterStudentsByContractTypeStrategy;
import br.com.noberto.upswing.util.verifications.AbstractCheckObject;
import br.com.noberto.upswing.util.verifications.CompanyCheck;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final EmailRepository repository;
    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final JobOfferEmailToSendStrategy jobOfferEmailToSend;
    private final CompanyEmailToSendStrategy companyEmailToSend;
    private final StudentEmailToSend studentEmailToSend;
    private final FilterStudentsByContractTypeStrategy filterStudentsByContractType;
    private final AbstractCheckObject companyCheck;
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public EmailService(EmailRepository repository, StudentRepository studentRepository, JavaMailSender mailSender,
                        CompanyRepository companyRepository, ZipCodeRepository zipCodeRepository, BusinessAreaRepository businessAreaRepository, CourseRepository courseRepository, ClassRepository classRepository, JobOfferEmailToSendStrategy jobOfferEmailToSend, CompanyEmailToSendStrategy companyEmailToSend, StudentEmailToSend studentEmailToSend, FilterStudentsByContractTypeStrategy filterStudentsByContractType, AbstractCheckObject companyCheck, EntityManager entityManager) {
        this.repository = repository;
        this.studentRepository = studentRepository;
        this.mailSender = mailSender;
        this.companyRepository = companyRepository;
        this.jobOfferEmailToSend = jobOfferEmailToSend;
        this.companyEmailToSend = companyEmailToSend;
        this.studentEmailToSend = studentEmailToSend;
        this.filterStudentsByContractType = filterStudentsByContractType;
        this.companyCheck = new CompanyCheck(zipCodeRepository, businessAreaRepository, studentRepository, classRepository, companyRepository, courseRepository, entityManager);
        this.entityManager = entityManager;
    }

    @Async("threadPoolTaskExecutor")
    public void welcomeEmail(Student studentData){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        Student student = studentRepository.findById(studentData.getId()).orElseThrow(() -> new EntityExistsException("ID informado para Aluno é invalido!"));
        EmailRequest emailRequest = studentEmailToSend.getWelcomeEmail(student);

        mailMessage.setFrom(emailRequest.getEmailFrom());
        mailMessage.setTo(student.getAccount().getEmail());
        mailMessage.setSubject(emailRequest.getSubject());
        mailMessage.setText(emailRequest.getMessage());
        mailSender.send(mailMessage);
        repository.save(new EmailSender(emailRequest));
    }


    public void emailForJobApplication(JobOffer jobOffer){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        Company company = companyRepository.getReferenceById(jobOffer.getCompany().getId());
        List<Student> students = filterStudentsByContractType.filterStudents(jobOffer);

        for (Student studentData : students){
            Student student = studentRepository.getReferenceById(studentData.getId());
            EmailRequest emailRequest = studentEmailToSend.getAppliedVacancyEmail(student, company, jobOffer);

            mailMessage.setFrom(emailRequest.getEmailFrom());
            mailMessage.setTo(student.getAccount().getEmail());
            mailMessage.setSubject(emailRequest.getSubject());
            mailMessage.setText(emailRequest.getMessage());

            mailSender.send(mailMessage);
            repository.save(new EmailSender(emailRequest));
        }
    }
    @Async("threadPoolTaskExecutor")
    public void emailForPendingProfile(Company company){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        EmailRequest emailRequest = companyEmailToSend.emailPending(company);

        mailMessage.setFrom(emailRequest.getEmailFrom());
        mailMessage.setTo(company.getAccount().getEmail());
        mailMessage.setSubject(emailRequest.getSubject());
        mailMessage.setText(emailRequest.getMessage());

        mailSender.send(mailMessage);
        repository.save(new EmailSender(emailRequest));
    }

    @Async("threadPoolTaskExecutor")
    public void emailForApprovedProfile(Company company){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        EmailRequest emailRequest = companyEmailToSend.emailApproved(company);

        mailMessage.setFrom(emailRequest.getEmailFrom());
        mailMessage.setTo(company.getAccount().getEmail());
        mailMessage.setSubject(emailRequest.getSubject());
        mailMessage.setText(emailRequest.getMessage());

        mailSender.send(mailMessage);
        repository.save(new EmailSender(emailRequest));
    }

    @Async("threadPoolTaskExecutor")
    public void emailForNotApprovedProfile(Company company){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        EmailRequest emailRequest = companyEmailToSend.emailNotApproved(company);

        mailMessage.setFrom(emailRequest.getEmailFrom());
        mailMessage.setTo(company.getAccount().getEmail());
        mailMessage.setSubject(emailRequest.getSubject());
        mailMessage.setText(emailRequest.getMessage());

        mailSender.send(mailMessage);
        repository.save(new EmailSender(emailRequest));
    }

    @Async("threadPoolTaskExecutor")
    public void emailForPendingVacancy(JobOffer jobOffer){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        Company company = companyRepository.findById(jobOffer.getCompany().getId())
                .orElseThrow(() -> new EntityExistsException("ID informado para Empresa é invalido!"));
        EmailRequest emailRequest = jobOfferEmailToSend.emailPending(jobOffer);

        mailMessage.setFrom(emailRequest.getEmailFrom());
        mailMessage.setTo(company.getAccount().getEmail());
        mailMessage.setSubject(emailRequest.getSubject());
        mailMessage.setText(emailRequest.getMessage());

        mailSender.send(mailMessage);
        repository.save(new EmailSender(emailRequest));
    }


    public void emailForApprovedVacancy(JobOffer jobOffer){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        Company company = companyRepository.findById(jobOffer.getCompany().getId())
                .orElseThrow(() -> new EntityExistsException("ID informado para Empresa é invalido!"));
        EmailRequest emailRequest = jobOfferEmailToSend.emailApproved(jobOffer);

        mailMessage.setFrom(emailRequest.getEmailFrom());
        mailMessage.setTo(company.getAccount().getEmail());
        mailMessage.setSubject(emailRequest.getSubject());
        mailMessage.setText(emailRequest.getMessage());

        mailSender.send(mailMessage);
        repository.save(new EmailSender(emailRequest));
    }

    @Async("threadPoolTaskExecutor")
    @Transactional
    public void emailForNotApprovedVacancy(JobOffer jobOffer){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        Company company = companyCheck.checkCompany(jobOffer.getCompany().getId());
        EmailRequest emailRequest = jobOfferEmailToSend.emailNotApproved(jobOffer);

        entityManager.flush();

        mailMessage.setFrom(emailRequest.getEmailFrom());
        mailMessage.setTo(company.getAccount().getEmail());
        mailMessage.setSubject(emailRequest.getSubject());
        mailMessage.setText(emailRequest.getMessage());

        mailSender.send(mailMessage);
        repository.save(new EmailSender(emailRequest));
    }
}
