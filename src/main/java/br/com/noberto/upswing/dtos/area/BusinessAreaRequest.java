package br.com.noberto.upswing.dtos.area;

import jakarta.validation.constraints.NotBlank;

public record BusinessAreaRequest(
        @NotBlank
        String area
) {
}
