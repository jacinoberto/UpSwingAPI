package br.com.noberto.upswing.util.filters;

import br.com.noberto.upswing.models.JobOffer;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.repositories.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class FilterStudentsWithCompatibilityStrategy implements IFilterStudentStrategy {
    private final FilterExistingStudentsOnCourseStrategy filterExistingStudentsOnCourse;
    private final StudentRepository studentRepository;

    public FilterStudentsWithCompatibilityStrategy(FilterExistingStudentsOnCourseStrategy filterExistingStudentsOnCourse, StudentRepository studentRepository, EntityManager entityManager) {
        this.filterExistingStudentsOnCourse = filterExistingStudentsOnCourse;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> filterStudents(JobOffer jobOffer) {
        List<Student> students = filterExistingStudentsOnCourse.filterStudents(jobOffer).stream()
                .filter(studentA -> studentRepository.findByStateTrue(jobOffer.getCompany().getId()).stream().anyMatch(studentA::equals))
                .distinct()
                .toList();

        return students.stream().filter(student -> studentRepository.findByAutoApplyTrue().stream().anyMatch(student::equals)).distinct().toList();
    }
}
