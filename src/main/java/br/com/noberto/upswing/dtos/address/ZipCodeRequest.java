package br.com.noberto.upswing.dtos.address;

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
}
