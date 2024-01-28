package br.com.noberto.upswing.services.list;

import br.com.noberto.upswing.dtos.admin.AdminResponse;
import br.com.noberto.upswing.models.Admin;
import br.com.noberto.upswing.models.Company;
import br.com.noberto.upswing.repositories.CompanyRepository;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Service
public class CompanyListService {
    private final CompanyRepository repository;

    CompanyListService(CompanyRepository repository){
        this.repository = repository;
    }

    public Company getCompany(UUID id){
        if (repository.existsById(id)){
            return repository.getReferenceById(id);
        }
        throw new ValidationException("ID informado da Empresa é invalido!");
    }
}
