package br.com.noberto.upswing.dtos.company;

import br.com.noberto.upswing.dtos.address.AddressRequest;
import br.com.noberto.upswing.models.Company;

public record CompanyResponse(
        String name,
        String socialOne,
        String socialTwo,
        String socialThree,
        String socialFour,
        String mainPhone,
        String email,
        String companyCode,
        AddressRequest address
) {
    public CompanyResponse (Company company){
        this(
                company.getAccount().getName(),
                company.getSocialNetworks().getSocialOne(),
                company.getSocialNetworks().getSocialTwo(),
                company.getSocialNetworks().getSocialThree(),
                company.getSocialNetworks().getSocialFour(),
                company.getAccount().getMainPhone(),
                company.getAccount().getEmail(),
                company.getCompanyCode(),
                new AddressRequest(company.getAddress())
        );
    }
}
