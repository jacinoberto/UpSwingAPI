package br.com.noberto.upswing.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailRequest {

    private String emailFrom = "upswing.contato@gmail.com";
    private String emailTo;
    private String subject;
    private String message;

    public EmailRequest(EmailSender email) {
        this.emailTo = email.getEmailTo();
        this.subject = email.getSubject();
        this.message = email.getMessage();
    }
}
