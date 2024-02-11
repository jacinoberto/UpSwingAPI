package br.com.noberto.upswing.services.mail;

import br.com.noberto.upswing.email.EmailRequest;
import br.com.noberto.upswing.email.EmailSender;
import br.com.noberto.upswing.models.*;
import br.com.noberto.upswing.repositories.*;
import br.com.noberto.upswing.util.emails.CompanyEmailToSendStrategy;
import br.com.noberto.upswing.util.emails.JobOfferEmailToSendStrategy;
import br.com.noberto.upswing.util.emails.StudentEmailToSend;
import br.com.noberto.upswing.util.filters.FilterStudentsByContractTypeStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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

    @Autowired
    public EmailService(EmailRepository repository, StudentRepository studentRepository, JavaMailSender mailSender,
                        CompanyRepository companyRepository, JobOfferEmailToSendStrategy jobOfferEmailToSend, CompanyEmailToSendStrategy companyEmailToSend, StudentEmailToSend studentEmailToSend, FilterStudentsByContractTypeStrategy filterStudentsByContractType) {
        this.repository = repository;
        this.studentRepository = studentRepository;
        this.mailSender = mailSender;
        this.companyRepository = companyRepository;
        this.jobOfferEmailToSend = jobOfferEmailToSend;
        this.companyEmailToSend = companyEmailToSend;
        this.studentEmailToSend = studentEmailToSend;
        this.filterStudentsByContractType = filterStudentsByContractType;
    }

    public void welcomeEmail(Student studentData){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        Student student = studentRepository.getReferenceById(studentData.getId());
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

    public void emailForPendingVacancy(JobOffer jobOffer){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        Company company = companyRepository.getReferenceById(jobOffer.getCompany().getId());
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
        Company company = companyRepository.getReferenceById(jobOffer.getCompany().getId());
        EmailRequest emailRequest = jobOfferEmailToSend.emailApproved(jobOffer);

        mailMessage.setFrom(emailRequest.getEmailFrom());
        mailMessage.setTo(company.getAccount().getEmail());
        mailMessage.setSubject(emailRequest.getSubject());
        mailMessage.setText(emailRequest.getMessage());

        mailSender.send(mailMessage);
        repository.save(new EmailSender(emailRequest));
    }

    public void emailForNotApprovedVacancy(JobOffer jobOffer){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        Company company = companyRepository.getReferenceById(jobOffer.getCompany().getId());
        EmailRequest emailRequest = jobOfferEmailToSend.emailNotApproved(jobOffer);

        mailMessage.setFrom(emailRequest.getEmailFrom());
        mailMessage.setTo(company.getAccount().getEmail());
        mailMessage.setSubject(emailRequest.getSubject());
        mailMessage.setText(emailRequest.getMessage());

        mailSender.send(mailMessage);
        repository.save(new EmailSender(emailRequest));
    }
}
