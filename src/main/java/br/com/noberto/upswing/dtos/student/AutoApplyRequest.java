package br.com.noberto.upswing.dtos.student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AutoApplyRequest(
        @NotBlank
        String contract,
        @NotBlank
        String offerLocation,
        @NotNull
        Boolean enableAutoApply,
        RegisterStudent student
) {
}
