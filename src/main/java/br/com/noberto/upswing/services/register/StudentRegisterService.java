package br.com.noberto.upswing.services.register;

import br.com.noberto.upswing.dtos.student.AutoApplyRequest;
import br.com.noberto.upswing.models.AutoApply;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.repositories.*;
import br.com.noberto.upswing.util.verifications.StudentCheck;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class StudentRegisterService {
    private final AutoApplyRepository autoApplyRepository;
    private final StudentCheck studentCheck;

    public StudentRegisterService(AutoApplyRepository autoApplyRepository, ZipCodeRepository zipCodeRepository,
                                  BusinessAreaRepository businessAreaRepository, StudentRepository studentRepository,
                                  ClassRepository classRepository, CompanyRepository companyRepository, CourseRepository
                                  courseRepository, EntityManager entityManager, StudentCheck studentCheck) {
        this.autoApplyRepository = autoApplyRepository;
        this.studentCheck = studentCheck;
    }

    public AutoApply insertAutoApply(AutoApplyRequest applyRequest){
        Student student = studentCheck.checkStudent(applyRequest.studentId());
        return autoApplyRepository.save(new AutoApply(applyRequest, student));
    }
}
