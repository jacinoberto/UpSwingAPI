package br.com.noberto.upswing.dtos.academic;

import br.com.noberto.upswing.dtos.student.StudentResponse;
import br.com.noberto.upswing.models.Class;
import br.com.noberto.upswing.models.Registration;
import br.com.noberto.upswing.models.Student;

import java.util.UUID;

public record RegistrationRequest(
        Integer registration,
        String email,
        UUID id
) {
    public RegistrationRequest (Registration registration){
        this(
                registration.getRegistration(),
                registration.getStudent().getAccount().getEmail(),
                registration.getAClass().getId()
        );
    }
}
