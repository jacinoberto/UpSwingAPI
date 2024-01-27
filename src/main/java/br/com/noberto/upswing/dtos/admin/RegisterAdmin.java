package br.com.noberto.upswing.dtos.admin;

import br.com.noberto.upswing.models.Admin;
import br.com.noberto.upswing.models.Student;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record RegisterAdmin (
        @NotBlank
        String position,
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
        @Email
        String mail,
        @NotBlank
        String password
) {
        public RegisterAdmin(Admin admin) {
                this(
                        admin.getAccount().getName(),
                        admin.getPosition(),
                        admin.getAccount().getBirthDate(),
                        admin.getAccount().getSocialSecurity(),
                        admin.getAccount().getMainPhone(),
                        admin.getAccount().getOptionalPhone(),
                        admin.getAccount().getMail(),
                        admin.getAccount().getPassword()
                );
        }
}