package br.com.noberto.upswing.dtos.company;

import br.com.noberto.upswing.dtos.address.AddressRequest;
import br.com.noberto.upswing.dtos.area.BusinessAreaRequest;
import br.com.noberto.upswing.enums.Status;
import br.com.noberto.upswing.models.Account;
import br.com.noberto.upswing.models.Company;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CNPJ;

public record RegisterCompany(
        @NotBlank
        String tradingName,
        Account account,
        @NotBlank
        @CNPJ
        String companyCode,
        BusinessAreaRequest businessArea,
        @NotBlank
        String description,
        AddressRequest address,
        String website,
        @NotBlank
        String status
) {
        public RegisterCompany(Company company) {
                this(
                        company.getTradingName(),
                        new Account(company),
                        company.getCompanyCode(),
                        new BusinessAreaRequest(company.getBusinessArea()),
                        company.getDescription(),
                        new AddressRequest(company.getAddress()),
                        company.getWebsite(),
                        Status.fromString(company.getStatus())
                );
        }
}
