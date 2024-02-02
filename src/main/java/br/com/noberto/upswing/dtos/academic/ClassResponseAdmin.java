package br.com.noberto.upswing.dtos.academic;

import br.com.noberto.upswing.enums.Mode;
import br.com.noberto.upswing.enums.Shift;
import br.com.noberto.upswing.models.Class;

import java.time.LocalDate;

public record ClassResponseAdmin(
        String courseName,
        Integer code,
        String mode,
        String shift,
        Integer vacancyNumber,
        LocalDate startDate,
        LocalDate closingDate
) {
    public ClassResponseAdmin (Class aClass){
        this(
                aClass.getCourse().getCourseName(),
                aClass.getCode(),
                Mode.fromString(aClass.getMode()),
                Shift.fromString(aClass.getShift()),
                aClass.getVacancyNumber(),
                aClass.getStartDate(),
                aClass.getClosingDate()
        );
    }
}
