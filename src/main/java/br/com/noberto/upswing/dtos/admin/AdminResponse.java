package br.com.noberto.upswing.dtos.admin;

import br.com.noberto.upswing.dtos.account.AccountResponse;
import br.com.noberto.upswing.models.Admin;

import java.util.UUID;

public record AdminResponse(
        String id,
        String position,
        AccountResponse account
) {
    public AdminResponse(Admin admin) {
        this(
                admin.getId(),
                admin.getPosition(),
                new AccountResponse(admin.getAccount())
        );
    }
}
