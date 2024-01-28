package br.com.noberto.upswing.services.list;

import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.repositories.StudentRepository;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentListService {
    private final StudentRepository repository;

    StudentListService(StudentRepository repository){
        this.repository = repository;
    }

    public Student getStudent(UUID id){
        if (repository.existsById(id)){
            return repository.getReferenceById(id);
        }
        throw new ValidationException("ID informado do Aluno é invalido!");
    }
}
