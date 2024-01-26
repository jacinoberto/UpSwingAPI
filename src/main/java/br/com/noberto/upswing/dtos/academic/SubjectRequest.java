package br.com.noberto.upswing.dtos.academic;

import jakarta.validation.constraints.NotBlank;

public record SubjectRequest(
        @NotBlank
        String subject,
        @NotBlank
        String description,
        CourseRequest course
) {
}
