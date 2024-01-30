package br.com.noberto.upswing.dtos.academic;

import br.com.noberto.upswing.enums.LearningMode;
import br.com.noberto.upswing.enums.Shift;
import br.com.noberto.upswing.models.Class;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record ClassRequest(
        @NotNull
        Integer code,
        @NotNull
        UUID courseId,
        @NotBlank
        String learningMode,
        @NotBlank
        String shift,
        @NotNull
        LocalDate startDate,
        LocalDate closingDate,
        @NotNull
        Integer vacancyNumber
) {
    public ClassRequest (Class aClass) {
        this(
                aClass.getCode(),
                aClass.getCourse().getId(),
                LearningMode.fromString(aClass.getLearningMode()),
                Shift.fromString(aClass.getShift()),
                aClass.getStartDate(),
                aClass.getClosingDate(),
                aClass.getVacancyNumber()
        );
    }
}
