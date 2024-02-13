package br.com.noberto.upswing.dtos.academic;

import br.com.noberto.upswing.models.CompletedSubject;

import java.util.UUID;

public record CompletedSubjectResponse(
        UUID completedSubjectId,
        UUID subjectId,
        String subject,
        UUID classId,
        Integer aClass,
        Boolean complete
) {
    public CompletedSubjectResponse(CompletedSubject completedSubject) {
        this(
                completedSubject.getId(),
                completedSubject.getSubject().getId(),
                completedSubject.getSubject().getSubjectName(),
                completedSubject.getAClass().getId(),
                completedSubject.getAClass().getCode(),
                completedSubject.getComplete()
        );
    }
}
