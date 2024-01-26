package br.com.noberto.upswing.dtos.academic;

import br.com.noberto.upswing.dtos.area.BusinessAreaRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CourseRequest(
        @NotBlank
        String course,
        BusinessAreaRequest businessAreaRequest,
        @NotBlank
        String educationLevel,
        @NotNull
        Integer workload,
        @NotNull
        BigDecimal monthlyCost,
        @NotNull
        BigDecimal totalCost
) {
}
