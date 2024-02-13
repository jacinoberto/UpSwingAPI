package br.com.noberto.upswing.dtos.student;

import br.com.noberto.upswing.dtos.address.AddressRequest;
import br.com.noberto.upswing.models.Account;

public record StudentUpdate(
        Account account,
        AddressRequest address
) {
}
