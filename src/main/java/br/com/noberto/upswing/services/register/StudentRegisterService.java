package br.com.noberto.upswing.services.register;

import br.com.noberto.upswing.dtos.student.AutoApplyRequest;
import br.com.noberto.upswing.models.AutoApply;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.repositories.AutoApplyRepository;
import br.com.noberto.upswing.repositories.StudentRepository;
import br.com.noberto.upswing.util.verifications.AdminCheck;
import br.com.noberto.upswing.util.verifications.StudentCheck;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentRegisterService {
    private final AutoApplyRepository autoApplyRepository;
    private final StudentCheck check;

    public StudentRegisterService(AutoApplyRepository autoApplyRepository, StudentCheck check) {
        this.autoApplyRepository = autoApplyRepository;
        this.check = check;
    }

    public AutoApply insertAutoApply(AutoApplyRequest applyRequest){
        Student student = check.checkStudent(applyRequest.studentId());
        return autoApplyRepository.save(new AutoApply(applyRequest, student));
    }
}
