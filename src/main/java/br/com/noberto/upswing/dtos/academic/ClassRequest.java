package br.com.noberto.upswing.dtos.academic;

import br.com.noberto.upswing.enums.Mode;
import br.com.noberto.upswing.enums.Shift;
import br.com.noberto.upswing.models.Class;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record ClassRequest(
        @NotNull
        UUID courseId,
        @NotBlank
        String mode,
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
                aClass.getCourse().getId(),
                Mode.fromString(aClass.getMode()),
                Shift.fromString(aClass.getShift()),
                aClass.getStartDate(),
                aClass.getClosingDate(),
                aClass.getVacancyNumber()
        );
    }
}
