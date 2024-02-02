package br.com.noberto.upswing.dtos.area;

import br.com.noberto.upswing.models.BusinessArea;

public record BusinessAreaResponse(
        String businessArea
) {
    public BusinessAreaResponse (BusinessArea businessArea){
        this(
                businessArea.getBusinessArea()
        );
    }
}
