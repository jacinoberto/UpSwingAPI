package br.com.noberto.upswing.dtos.academic;

import br.com.noberto.upswing.models.Subject;

import java.util.List;

public record SubjectResponse(
        String subjectName
) {
    public SubjectResponse (Subject subject){
        this(subject.getSubjectName());
    }
}
