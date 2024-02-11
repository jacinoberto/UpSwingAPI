package br.com.noberto.upswing.email;

import br.com.noberto.upswing.models.Company;
import br.com.noberto.upswing.models.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_emails")
@AllArgsConstructor @NoArgsConstructor @Data
public class EmailSender {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_email")
    private UUID id;

    @Column(name = "email_from")
    private String emailFrom;

    @Column(name = "email_to")
    private String emailTo;

    private String subject;

    private String message;

    @Column(name = "date_of_dispatch")
    private LocalDate dateOfDispatch;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public EmailSender(EmailRequest email) {
        this.emailFrom = "upswing.contato@gmail.com";
        this.emailTo = email.getEmailTo();
        this.subject = email.getSubject();
        this.message = email.getMessage();
        this.dateOfDispatch = LocalDate.now();
    }
}
