package br.com.noberto.upswing.util.emails;

import br.com.noberto.upswing.email.EmailRequest;

public interface IEmailToSendStrategy {

    <T> EmailRequest emailPending(T object);
    <T> EmailRequest emailApproved(T object);
    <T> EmailRequest emailNotApproved(T object);
}
