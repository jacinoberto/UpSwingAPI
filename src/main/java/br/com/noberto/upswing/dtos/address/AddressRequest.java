package br.com.noberto.upswing.dtos.address;

import br.com.noberto.upswing.models.Address;
import br.com.noberto.upswing.models.ZipCode;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddressRequest(
        UUID id,
        @NotNull
        Integer number,
        String complement,
        ZipCodeRequest zipCode
) {
        public AddressRequest(Address address) {
                this(
                        address.getId(),
                        address.getNumber(),
                        address.getComplement(),
                        new ZipCodeRequest(address.getZipCode())
                );
        }
}
