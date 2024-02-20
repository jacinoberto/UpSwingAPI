package br.com.noberto.upswing.dtos.academic;

import br.com.noberto.upswing.models.Registration;

import java.util.UUID;

public record RegistrationReturn(
        UUID classId,
        Integer registration,
        String email
) {
    public RegistrationReturn(Registration registration) {
        this(
                registration.getAClass().getId(),
                registration.getRegistration(),
                registration.getStudent().getAccount().getEmail()
        );
    }
}
