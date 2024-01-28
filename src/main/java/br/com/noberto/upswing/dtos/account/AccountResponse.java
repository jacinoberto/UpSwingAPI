package br.com.noberto.upswing.dtos.account;

import br.com.noberto.upswing.models.Account;

public record AccountResponse(
        String name,
        String mainPhone,
        String optionalPhone,
        String mail
) {

    public AccountResponse(Account account) {
        this(
                account.getName(),
                account.getMainPhone(),
                account.getOptionalPhone(),
                account.getEmail()
        );
    }
}
