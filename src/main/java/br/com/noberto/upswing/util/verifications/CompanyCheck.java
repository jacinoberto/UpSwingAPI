package br.com.noberto.upswing.util.verifications;

import br.com.noberto.upswing.dtos.address.AddressRequest;
import br.com.noberto.upswing.models.Address;
import br.com.noberto.upswing.models.BusinessArea;
import br.com.noberto.upswing.models.Company;
import br.com.noberto.upswing.models.ZipCode;
import br.com.noberto.upswing.repositories.BusinessAreaRepository;
import br.com.noberto.upswing.repositories.CompanyRepository;
import br.com.noberto.upswing.repositories.ZipCodeRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CompanyCheck implements ICheckObjectStrategy{
    private final BusinessAreaRepository businessAreaRepository;
    private final ZipCodeRepository zipCodeRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyCheck(BusinessAreaRepository businessAreaRepository, ZipCodeRepository zipCodeRepository, CompanyRepository companyRepository) {
        this.businessAreaRepository = businessAreaRepository;
        this.zipCodeRepository = zipCodeRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public Address checkZipCode(AddressRequest address) {
        ZipCode zipCode = null;

        if (zipCodeRepository.existsById(address.zipCode().zipCode())){
            zipCode = zipCodeRepository.getReferenceById(address.zipCode().zipCode());
        } else {
            zipCode = zipCodeRepository.save(new ZipCode(address.zipCode()));
        }

        return new Address(address, zipCode);
    }

    @Override
    public BusinessArea checkBusinessArea(UUID businessAreaId) {
        if (businessAreaRepository.existsById(businessAreaId)){
            return businessAreaRepository.getReferenceById(businessAreaId);
        }

        throw new ValidationException("ID informado para Área de Atuação é invalido !");
    }

    public Company checkCompany(UUID companyId){
        if (companyRepository.existsById(companyId)){
            return companyRepository.getReferenceById(companyId);
        }

        throw new ValidationException("ID informado para Empresa é invalido!");
    }
}
