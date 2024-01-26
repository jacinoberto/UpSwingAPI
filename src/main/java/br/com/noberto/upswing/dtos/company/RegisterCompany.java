package br.com.noberto.upswing.dtos.company;

import br.com.noberto.upswing.dtos.address.AddressRrequest;
import br.com.noberto.upswing.dtos.area.AreaOfOperationRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;

public record RegisterCompany(
        @NotBlank
        String corporateName,
        @NotBlank
        String fantasyName,
        @NotBlank
        @CNPJ
        String cnpj,
        AreaOfOperationRequest areaOfOperation,
        @NotBlank
        String description,
        AddressRrequest address,
        String site,
        @NotBlank
        String mainPhone,
        String optionalPhone,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password,
        @NotNull
        Boolean activeProfile
) {
}
