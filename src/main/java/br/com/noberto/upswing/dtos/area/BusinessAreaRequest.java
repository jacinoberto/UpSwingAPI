package br.com.noberto.upswing.dtos.area;

import br.com.noberto.upswing.models.BusinessArea;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record BusinessAreaRequest(
        UUID id,
        String businessArea
) {
        public BusinessAreaRequest(BusinessArea area) {
                this(
                        area.getId(),
                        area.getBusinessArea()
                );
        }
}
