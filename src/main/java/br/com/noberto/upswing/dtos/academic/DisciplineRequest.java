package br.com.noberto.upswing.dtos.academic;

import jakarta.validation.constraints.NotBlank;

public record DisciplineRequest(
        @NotBlank
        String discipline,
        @NotBlank
        String description,
        CourseRequest course
) {
}
