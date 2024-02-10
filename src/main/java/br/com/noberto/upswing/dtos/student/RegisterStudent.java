package br.com.noberto.upswing.dtos.student;

import br.com.noberto.upswing.dtos.address.AddressRequest;
import br.com.noberto.upswing.models.Account;
import br.com.noberto.upswing.models.Student;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.UUID;

public record RegisterStudent(
        UUID id,
        @NotNull
        LocalDate birthDate,
        @NotBlank
        @CPF
        String socialSecurity,

        Account account,
        AddressRequest address
) {
        public RegisterStudent(Student student) {
                this(
                        student.getId(),
                        student.getBirthDate(),
                        student.getSocialSecurity(),
                        new Account(student),
                        new AddressRequest(student.getAddress())
                );
        }
}
