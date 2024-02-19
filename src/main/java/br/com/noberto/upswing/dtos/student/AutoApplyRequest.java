package br.com.noberto.upswing.dtos.student;

import br.com.noberto.upswing.enums.Contract;
import br.com.noberto.upswing.enums.Location;
import br.com.noberto.upswing.models.AutoApply;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AutoApplyRequest(
        String contract,
        String offerLocation,
        String studentId
) {
        public AutoApplyRequest(AutoApply autoApply){
                this(
                        Contract.fromString(autoApply.getContract()),
                        Location.fromString(autoApply.getOfferLocation()),
                        autoApply.getStudent().getId()
                );
        }
}
