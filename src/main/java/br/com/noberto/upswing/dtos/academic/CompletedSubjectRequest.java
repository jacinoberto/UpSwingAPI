package br.com.noberto.upswing.dtos.academic;

import br.com.noberto.upswing.models.CompletedSubject;

import java.util.UUID;

public record CompletedSubjectRequest(
        UUID id,
        UUID subjectId,
        UUID classId,
        Boolean complete

) {
    public CompletedSubjectRequest(CompletedSubject completedSubject) {
        this(
                completedSubject.getId(),
                completedSubject.getSubject().getId(),
                completedSubject.getAClass().getId(),
                completedSubject.getComplete()
        );
    }
}
