package br.com.noberto.upswing.services.list;

import br.com.noberto.upswing.models.JobOffer;
import br.com.noberto.upswing.models.NullSocialNetworks;
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

    public Student getStudent(String id){
        if (repository.existsById(id)){
            Student student = repository.getReferenceById(id);
            if (student.getSocialNetworks() == null){
                student.setSocialNetworks(new NullSocialNetworks());
            }
            return student;
        }
        throw new ValidationException("ID informado do Aluno é invalido!");
    }

}
