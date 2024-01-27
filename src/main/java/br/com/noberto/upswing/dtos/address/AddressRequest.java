package br.com.noberto.upswing.dtos.address;

import br.com.noberto.upswing.models.Address;
import br.com.noberto.upswing.models.ZipCode;
import jakarta.validation.constraints.NotNull;

public record AddressRequest(
        @NotNull
        Integer number,
        String complement,
        ZipCodeRequest zipCode
) {
        public AddressRequest(Address address) {
                this(
                        address.getNumber(),
                        address.getComplement(),
                        new ZipCodeRequest(address.getZipCode())
                );
        }
}
