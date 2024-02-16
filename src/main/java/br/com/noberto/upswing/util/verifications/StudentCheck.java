package br.com.noberto.upswing.util.verifications;

import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.repositories.*;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StudentCheck extends AbstractCheckObject{
    public StudentCheck(ZipCodeRepository zipCodeRepository, BusinessAreaRepository businessAreaRepository, StudentRepository studentRepository, ClassRepository classRepository, CompanyRepository companyRepository, CourseRepository courseRepository, EntityManager entityManager) {
        super(zipCodeRepository, businessAreaRepository, studentRepository, classRepository, companyRepository, courseRepository, entityManager);
    }

    @Override
    public Student checkStudent(String studentId) {
        return super.checkStudent(studentId);
    }
}
