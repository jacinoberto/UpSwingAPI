package br.com.noberto.upswing.util.verifications;

import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.repositories.StudentRepository;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StudentCheck {
    private final StudentRepository studentRepository;

    public StudentCheck(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student checkStudent(UUID studentId){
        if (studentRepository.existsById(studentId)){
            return studentRepository.getReferenceById(studentId);
        }
        throw new ValidationException("ID informado para Aluno é invalido!");
    }
}
