package br.com.noberto.upswing.util.emails;

import br.com.noberto.upswing.email.EmailRequest;

public interface IEmailToSendStrategy {

    String emailPending(Object object);
    String emailApproved(Object object);
    String emailNotApproved(Object object);
}
