package br.com.noberto.upswing.dtos.student;

import br.com.noberto.upswing.dtos.address.AddressRequest;
import br.com.noberto.upswing.models.Student;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record RegisterStudent(
        @NotBlank
        String name,
        @NotNull
        LocalDate birthDate,
        @NotBlank
        @CPF
        String socialSecurity,
        @NotBlank
        String mainPhone,
        String optionalPhone,
        AddressRequest address,
        @NotBlank
        @Email
        String mail,
        @NotBlank
        String password
) {
        public RegisterStudent(Student student) {
                this(
                        student.getAccount().getName(),
                        student.getAccount().getBirthDate(),
                        student.getAccount().getSocialSecurity(),
                        student.getAccount().getMainPhone(),
                        student.getAccount().getOptionalPhone(),
                        new AddressRequest(student.getAddress()),
                        student.getAccount().getMail(),
                        student.getAccount().getPassword()
                );
        }
}
