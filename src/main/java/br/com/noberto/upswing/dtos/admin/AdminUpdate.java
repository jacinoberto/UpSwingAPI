package br.com.noberto.upswing.dtos.admin;

import br.com.noberto.upswing.models.Account;
import br.com.noberto.upswing.models.Admin;

public record AdminUpdate(
    String position,
    Account account
) {
    public AdminUpdate(Admin admin) {
        this(
                admin.getPosition(),
                admin.getAccount()
        );
    }
}
