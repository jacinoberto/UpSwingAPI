package br.com.noberto.upswing.services.delete;

import br.com.noberto.upswing.models.Account;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentDeleteService {
    private final StudentRepository repository;

    StudentDeleteService(StudentRepository repository){
        this.repository = repository;
    }

    public void disableStudent(UUID id){
        Student student = repository.getReferenceById(id);
        Account account = new Account(student);
        account.setActiveProfile(false);
        student.setAccount(account);
    }
}
