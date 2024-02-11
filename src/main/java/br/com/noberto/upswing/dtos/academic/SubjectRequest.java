package br.com.noberto.upswing.dtos.academic;

import br.com.noberto.upswing.models.Subject;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record SubjectRequest(
        UUID id,
        @NotBlank
        String subjectName,
        @NotBlank
        String description,
        UUID courseId
) {
        public SubjectRequest(Subject subject) {
                this(
                        subject.getId(),
                        subject.getSubjectName(),
                        subject.getDescription(),
                        subject.getCourse().getId()
                );
        }
}
