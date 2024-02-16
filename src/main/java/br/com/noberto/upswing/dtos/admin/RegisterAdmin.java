package br.com.noberto.upswing.dtos.admin;

import br.com.noberto.upswing.models.Account;
import br.com.noberto.upswing.models.Admin;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record RegisterAdmin (
        String id,
        @NotBlank
        String position,
        Account account
) {
        public RegisterAdmin(Admin admin) {
                this(
                        admin.getId(),
                        admin.getPosition(),
                        new Account(admin)
                );
        }
}