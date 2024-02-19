package br.com.noberto.upswing.util.verifications;

import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.repositories.*;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class StudentCheck {
    private final StudentRepository studentRepository;
    private final EntityManager entityManager;

    public StudentCheck(StudentRepository studentRepository, EntityManager entityManager) {
        this.studentRepository = studentRepository;
        this.entityManager = entityManager;
    }

    @Transactional
    public Student checkStudent(String studentId) {
        entityManager.flush();
        Student student = new Student();
        if (studentRepository.findById(studentId).isPresent()){
            student =  studentRepository.findById(studentId)
                    .orElseThrow(() -> new EntityExistsException("Id informado para aluno é invalido!"));
        }

        return student;
    }

}
