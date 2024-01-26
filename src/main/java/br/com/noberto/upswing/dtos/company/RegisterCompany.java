package br.com.noberto.upswing.dtos.company;

import br.com.noberto.upswing.dtos.address.AddressRrequest;
import br.com.noberto.upswing.dtos.area.BusinessAreaRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CNPJ;

public record RegisterCompany(
        @NotBlank
        String companyName,
        @NotBlank
        String tradingName,
        @NotBlank
        @CNPJ
        @UniqueElements
        String companyCode,
        BusinessAreaRequest businessArea,
        @NotBlank
        String description,
        AddressRrequest address,
        String website,
        @NotBlank
        String mainPhone,
        String optionalPhone,
        @NotBlank
        @Email
        @UniqueElements
        String mail,
        @NotBlank
        String password,
        @NotNull
        Boolean activeProfile
) {
}
