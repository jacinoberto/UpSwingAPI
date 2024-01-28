package br.com.noberto.upswing.dtos.admin;

import br.com.noberto.upswing.models.Account;
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
        Account account
) {
        public RegisterAdmin(Admin admin) {
                this(
                        admin.getPosition(),
                        new Account(admin)
                );
        }
}