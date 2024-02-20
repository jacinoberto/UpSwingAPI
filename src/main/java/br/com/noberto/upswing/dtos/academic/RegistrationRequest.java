package br.com.noberto.upswing.dtos.academic;

import br.com.noberto.upswing.dtos.student.StudentResponse;
import br.com.noberto.upswing.models.Class;
import br.com.noberto.upswing.models.Registration;
import br.com.noberto.upswing.models.Student;

import java.util.List;
import java.util.UUID;

public record RegistrationRequest(
        Integer registration,
        List<String> emails,
        UUID classId
) {
    public RegistrationRequest (Registration registration){
        this(
                registration.getRegistration(),
                registration.getStudent().getRegistrations().stream()
                        .map(Registration -> registration.getStudent().getAccount().getEmail())
                        .toList(),
                registration.getAClass().getId()
        );
    }
}
