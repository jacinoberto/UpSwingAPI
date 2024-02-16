package br.com.noberto.upswing.dtos.company;

import br.com.noberto.upswing.dtos.address.AddressRequest;
import br.com.noberto.upswing.models.Company;
import br.com.noberto.upswing.models.SocialNetworks;

import java.util.UUID;

public record CompanyResponse(
        String id,
        String name,
        SocialNetworks socialNetworks,
        String mainPhone,
        String email,
        String companyCode,
        AddressRequest address
) {
    public CompanyResponse (Company company){
        this(
                company.getId(),
                company.getAccount().getName(),
                company.getSocialNetworks(),
                company.getAccount().getMainPhone(),
                company.getAccount().getEmail(),
                company.getCompanyCode(),
                new AddressRequest(company.getAddress())
        );
    }
}
