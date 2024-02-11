package br.com.noberto.upswing.util.filters;

import br.com.noberto.upswing.models.JobOffer;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.repositories.StudentRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilterStudentsWithCompatibilityStrategy implements IFilterStudentStrategy {
    private final FilterExistingStudentsOnCourseStrategy filterExistingStudentsOnCourse;
    private final StudentRepository studentRepository;

    public FilterStudentsWithCompatibilityStrategy(FilterExistingStudentsOnCourseStrategy filterExistingStudentsOnCourse, StudentRepository studentRepository) {
        this.filterExistingStudentsOnCourse = filterExistingStudentsOnCourse;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> filterStudents(JobOffer jobOffer) {
        return filterExistingStudentsOnCourse.filterStudents(jobOffer).stream()
                .filter(studentA -> studentRepository.findByStateTrue(jobOffer.getCompany().getId()).stream()
                        .anyMatch(studentA::equals) || studentRepository.findByAutoApplyTrue().stream().anyMatch(studentA::equals))
                .distinct()
                .toList();
    }
}
