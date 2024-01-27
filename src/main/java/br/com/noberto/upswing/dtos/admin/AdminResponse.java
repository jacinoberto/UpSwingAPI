package br.com.noberto.upswing.dtos.admin;

import br.com.noberto.upswing.models.Admin;

public record AdminResponse(
        String name,
        String position,
        String mainPhone,
        String optionalPhone,
        String mail
) {
    public AdminResponse(Admin admin) {
        this(
                admin.getAccount().getName(),
                admin.getPosition(),
                admin.getAccount().getMainPhone(),
                admin.getAccount().getOptionalPhone(),
                admin.getAccount().getMail()
        );
    }
}
