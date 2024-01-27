package br.com.noberto.upswing.services.list;

import br.com.noberto.upswing.models.Admin;
import br.com.noberto.upswing.repositories.AdminRepository;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

@Service
public class AdminListService {
    private final AdminRepository repository;

    AdminListService(AdminRepository repository){
        this.repository = repository;
    }

    public Admin getAdmin(UUID id){
        if (repository.existsById(id)){
            return repository.getReferenceById(id);
        }
        throw new ValidationException("ID informado é invalido!");
    }
}
