package br.com.noberto.upswing.services.list;

import br.com.noberto.upswing.enums.Contract;
import br.com.noberto.upswing.enums.Location;
import br.com.noberto.upswing.models.*;
import br.com.noberto.upswing.repositories.AutoApplyRepository;
import br.com.noberto.upswing.repositories.CompanyRepository;
import br.com.noberto.upswing.repositories.JobOfferRepository;
import br.com.noberto.upswing.repositories.StudentRepository;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class CompanyListService {
    private final CompanyRepository repository;

    CompanyListService(CompanyRepository repository){
        this.repository = repository;
    }

    public Company getCompany(UUID id){
        if (repository.existsById(id)){
            Company company = repository.getReferenceById(id);
            if (company.getSocialNetworks() == null){
                company.setSocialNetworks(new NullSocialNetworks());
            }
            return company;
        }
        throw new ValidationException("ID informado da Empresa é invalido!");
    }
}
