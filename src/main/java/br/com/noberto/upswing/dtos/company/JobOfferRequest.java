package br.com.noberto.upswing.dtos.company;

import br.com.noberto.upswing.dtos.area.BusinessAreaRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record JobOfferRequest(
        RegisterCompany company,
        @NotBlank
        String position,
        BusinessAreaRequest businessArea,
        @NotBlank
        String educationLevel,
        @NotBlank
        String contract,
        @NotNull
        BigDecimal salary,
        @NotNull
        Boolean disability,
        @NotNull
        Integer offerQty,
        @NotNull
        LocalDate deadline,
        @NotBlank
        String description,
        @NotBlank
        String assignedFunctions,
        @NotNull
        Boolean benefitsMealVoucher ,
        @NotNull
        Boolean benefitsFoodVoucher,
        @NotNull
        Boolean benefitsTransportAllowance,
        @NotNull
        Boolean benefitsCulture,
        @NotNull
        Boolean benefitsEducation,
        @NotNull
        Boolean benefitsHealthInsurance
) {
}
