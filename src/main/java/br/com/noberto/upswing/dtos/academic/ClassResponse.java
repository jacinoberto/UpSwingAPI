package br.com.noberto.upswing.dtos.academic;

import br.com.noberto.upswing.models.Class;

public record ClassResponse(
        Integer code
) {
    public ClassResponse (Class aClass){
        this(
                aClass.getCode()
        );
    }
}
