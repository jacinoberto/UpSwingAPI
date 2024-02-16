package br.com.noberto.upswing.services.list;

import br.com.noberto.upswing.dtos.academic.CourseSelect;
import br.com.noberto.upswing.dtos.student.StudentResponseCompany;
import br.com.noberto.upswing.models.*;
import br.com.noberto.upswing.repositories.*;
import jakarta.validation.ValidationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CompanyListService {
    private final CompanyRepository repository;

    CompanyListService(CompanyRepository repository){
        this.repository = repository;
    }

    public Company getCompany(String id){
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
