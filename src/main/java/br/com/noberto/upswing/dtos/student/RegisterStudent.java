package br.com.noberto.upswing.dtos.student;

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
        @NotBlank
        String zipCode,
        @NotBlank
        String street,
        @NotNull
        Integer number,
        String complement,
        @NotBlank
        String area,
        @NotBlank
        String city,
        @NotBlank
        String state,
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
                        student.getAddress().getZipCode().getZipCode(),
                        student.getAddress().getZipCode().getStreet(),
                        student.getAddress().getNumber(),
                        student.getAddress().getComplement(),
                        student.getAddress().getZipCode().getArea(),
                        student.getAddress().getZipCode().getCity(),
                        student.getAddress().getZipCode().getState(),
                        student.getAccount().getMail(),
                        student.getAccount().getPassword()
                );
        }
}
