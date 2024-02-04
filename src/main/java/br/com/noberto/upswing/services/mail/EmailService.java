package br.com.noberto.upswing.services.mail;

import br.com.noberto.upswing.mail.EmailRequest;
import br.com.noberto.upswing.mail.EmailSender;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.repositories.EmailRepository;
import br.com.noberto.upswing.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final EmailRepository repository;
    private final StudentRepository studentRepository;

    @Autowired
    public EmailService(EmailRepository repository, StudentRepository studentRepository, JavaMailSender mailSender) {
        this.repository = repository;
        this.studentRepository = studentRepository;
        this.mailSender = mailSender;
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
}
