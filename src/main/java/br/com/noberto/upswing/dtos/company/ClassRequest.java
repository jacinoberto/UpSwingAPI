package br.com.noberto.upswing.dtos.company;

import br.com.noberto.upswing.dtos.academic.CourseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ClassRequest(
        @NotNull
        Integer code,
        CourseRequest course,
        @NotBlank
        String category,
        @NotBlank
        String shift,
        @NotNull
        LocalDate startDate,
        LocalDate finalDate,
        @NotNull
        Integer numberOfVacancies
) {
}
