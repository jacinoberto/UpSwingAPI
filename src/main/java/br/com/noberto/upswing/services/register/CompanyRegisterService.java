package br.com.noberto.upswing.services.register;

import br.com.noberto.upswing.dtos.company.RegisterCompany;
import br.com.noberto.upswing.dtos.student.RegisterStudent;
import br.com.noberto.upswing.models.Address;
import br.com.noberto.upswing.models.BusinessArea;
import br.com.noberto.upswing.models.Company;
import br.com.noberto.upswing.models.ZipCode;
import br.com.noberto.upswing.repositories.BusinessAreaRepository;
import br.com.noberto.upswing.repositories.CompanyRepository;
import br.com.noberto.upswing.repositories.ZipCodeRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyRegisterService {
    private final CompanyRepository repository;
    private final ZipCodeRepository zipCodeRepository;
    private final BusinessAreaRepository businessAreaRepository;

    @Autowired
    CompanyRegisterService(CompanyRepository repository, ZipCodeRepository zipCodeRepository, BusinessAreaRepository
            businessAreaRepository){
        this.repository = repository;
        this.zipCodeRepository = zipCodeRepository;
        this.businessAreaRepository = businessAreaRepository;
    }

    public Company registerCompany(RegisterCompany registerCompany){
        Address address = checkAddress(registerCompany);
        Company company = new Company(registerCompany);
        company.setBusinessArea(checkBusinessArea(registerCompany));
        company.setAddress(address);

        return repository.save(company);
    }

    //METHODS
    private Address checkAddress(RegisterCompany data){
        ZipCode zipCode = null;

        if (zipCodeRepository.existsById(data.address().zipCode().zipCode())){
            zipCode = zipCodeRepository.getReferenceById(data.address().zipCode().zipCode());
        } else {
            zipCode = zipCodeRepository.save(new ZipCode(data));
        }

        return new Address(data, zipCode);
    }

    private BusinessArea checkBusinessArea(RegisterCompany data){
        if (businessAreaRepository.existsById(data.businessArea().id())){
            return businessAreaRepository.getReferenceById(data.businessArea().id());
        }

        throw new ValidationException("ID informado é invalido!");
    }
}
