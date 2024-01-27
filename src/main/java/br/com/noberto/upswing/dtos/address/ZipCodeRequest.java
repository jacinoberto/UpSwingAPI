package br.com.noberto.upswing.dtos.address;

import br.com.noberto.upswing.models.ZipCode;
import jakarta.validation.constraints.NotBlank;

public record ZipCodeRequest(
        @NotBlank
        String zipCode,
        @NotBlank
        String street,
        @NotBlank
        String area,
        @NotBlank
        String city,
        @NotBlank
        String state
) {
        public ZipCodeRequest (ZipCode zipCode) {
                this(
                        zipCode.getZipCode(),
                        zipCode.getStreet(),
                        zipCode.getArea(),
                        zipCode.getCity(),
                        zipCode.getState()
                );
        }
}
