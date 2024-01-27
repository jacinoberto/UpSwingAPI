package br.com.noberto.upswing.dtos.address;

import br.com.noberto.upswing.models.Address;
import br.com.noberto.upswing.models.ZipCode;
import jakarta.validation.constraints.NotNull;

public record AddressRequest(
        @NotNull
        Integer number,

        String complement,
        @NotNull
        ZipCodeRequest zipCode
) {
}
