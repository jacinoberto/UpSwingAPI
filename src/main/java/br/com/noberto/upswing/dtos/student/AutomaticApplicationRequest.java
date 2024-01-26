package br.com.noberto.upswing.dtos.student;

import br.com.noberto.upswing.enums.Contract;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AutomaticApplicationRequest(
        @NotNull
        Boolean enrolledCourse,
        @NotBlank
        String contract,
        @NotBlank
        String offerLocation,
        @NotNull
        RegisterStudent student
) {
}
