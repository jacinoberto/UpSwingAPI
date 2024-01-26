package br.com.noberto.upswing.dtos.academic;

import br.com.noberto.upswing.dtos.area.AreaOfOperationRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CourseRequest(
        @NotBlank
        String course,
        AreaOfOperationRequest areaOfOperation,
        @NotBlank
        String educationDegree,
        @NotNull
        Integer workload,
        @NotNull
        BigDecimal monthlyValue,
        @NotNull
        BigDecimal totalValue
) {
}
