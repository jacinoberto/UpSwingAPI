package br.com.noberto.upswing.dtos.account;

import br.com.noberto.upswing.models.Account;
import br.com.noberto.upswing.models.Admin;

import java.time.LocalDate;
import java.util.UUID;

public record AccountResponse(
        String name,
        LocalDate birthDate,
        String mainPhone,
        String optionalPhone,
        String mail
) {

    public AccountResponse(Account account) {
        this(
                account.getName(),
                account.getBirthDate(),
                account.getMainPhone(),
                account.getOptionalPhone(),
                account.getMail()
        );
    }
}
