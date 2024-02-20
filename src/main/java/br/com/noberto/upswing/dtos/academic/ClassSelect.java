package br.com.noberto.upswing.dtos.academic;

import br.com.noberto.upswing.models.Class;

import java.util.UUID;

public record ClassSelect(
        UUID classId,
        Integer code,
        String courseName
) {
    public ClassSelect(Class aClass) {
        this(
                aClass.getId(),
                aClass.getCode(),
                aClass.getCourse().getCourseName()
        );
    }
}
