package br.com.noberto.upswing.dtos.admin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
        String password,
        @NotNull
        Boolean activeProfile
) {
}
