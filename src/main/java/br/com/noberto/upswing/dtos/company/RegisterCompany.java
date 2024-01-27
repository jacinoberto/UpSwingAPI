package br.com.noberto.upswing.dtos.company;

import br.com.noberto.upswing.dtos.address.AddressRequest;
import br.com.noberto.upswing.dtos.area.BusinessAreaRequest;
import br.com.noberto.upswing.models.Company;
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
        String companyCode,
        BusinessAreaRequest businessArea,
        //@NotBlank
        String description,
        AddressRequest address,
        String website,
        @NotBlank
        String mainPhone,
        String optionalPhone,
        @NotBlank
        @Email
        String mail,
        @NotBlank
        String password
) {
        public RegisterCompany(Company company) {
                this(
                        company.getCompanyName(),
                        company.getTradingName(),
                        company.getCompanyCode(),
                        new BusinessAreaRequest(company.getBusinessArea()),
                        company.getDescription(),
                        new AddressRequest(company.getAddress()),
                        company.getWebsite(),
                        company.getMainPhone(),
                        company.getOptionalPhone(),
                        company.getMail(),
                        company.getPassword()
                );
        }
}
