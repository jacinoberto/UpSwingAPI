package br.com.noberto.upswing.dtos.address;

import jakarta.validation.constraints.NotNull;

public record AddressRrequest(
        @NotNull
        Integer number,

        String complement,
        @NotNull
        ZipCodeRequest zipCode
) {
}
