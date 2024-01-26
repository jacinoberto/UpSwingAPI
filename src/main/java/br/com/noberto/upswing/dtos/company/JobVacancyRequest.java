package br.com.noberto.upswing.dtos.company;

import br.com.noberto.upswing.dtos.area.AreaOfOperationRequest;
import br.com.noberto.upswing.models.AreaOfOperation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record JobVacancyRequest(
        RegisterCompany company,
        @NotBlank
        String occupation,
        AreaOfOperationRequest areaOfOperation,
        @NotBlank
        String educationDegree,
        @NotBlank
        String contract,
        @NotNull
        BigDecimal remuneration,
        @NotNull
        Boolean disabledPerson,
        @NotNull
        Integer numberOfVacancies,
        @NotNull
        LocalDate deadline,
        @NotBlank
        String description,
        @NotBlank
        String assignedFunctions,
        @NotNull
        Boolean meal,
        @NotNull
        Boolean foodVoucher,
        @NotNull
        Boolean transportationAllowance,
        @NotNull
        Boolean culture,
        @NotNull
        Boolean education,
        @NotNull
        Boolean healthPlan
) {
}
