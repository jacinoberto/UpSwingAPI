package br.com.noberto.upswing.services.register;

import br.com.noberto.upswing.dtos.student.AutoApplyRequest;
import br.com.noberto.upswing.models.AutoApply;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.repositories.AutoApplyRepository;
import br.com.noberto.upswing.repositories.StudentRepository;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentRegisterService {
    private final AutoApplyRepository autoApplyRepository;
    private final StudentRepository studentRepository;

    public StudentRegisterService(AutoApplyRepository autoApplyRepository, StudentRepository studentRepository) {
        this.autoApplyRepository = autoApplyRepository;
        this.studentRepository = studentRepository;
    }

    public AutoApply insertAutoApply(AutoApplyRequest applyRequest){
        Student student = checkStudent(applyRequest.studentId());
        return autoApplyRepository.save(new AutoApply(applyRequest, student));
    }

    public Student checkStudent(UUID id){
        if (studentRepository.existsById(id)){
            return studentRepository.getReferenceById(id);
        }
        throw new ValidationException("Id informado para Aluno é invalido");
    }
}
