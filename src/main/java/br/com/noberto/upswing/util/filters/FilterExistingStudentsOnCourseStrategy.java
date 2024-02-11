package br.com.noberto.upswing.util.filters;

import br.com.noberto.upswing.dtos.academic.CourseSelect;
import br.com.noberto.upswing.models.JobOffer;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.repositories.StudentRepository;
import br.com.noberto.upswing.repositories.VacancyAndCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FilterExistingStudentsOnCourseStrategy implements IFilterStudentStrategy {
    private static StudentRepository repository;
    private static VacancyAndCourseRepository vacancyAndCourseRepository;


    @Autowired
    public FilterExistingStudentsOnCourseStrategy(StudentRepository repository, VacancyAndCourseRepository vacancyAndCourseRepository) {
        FilterExistingStudentsOnCourseStrategy.repository = repository;
        FilterExistingStudentsOnCourseStrategy.vacancyAndCourseRepository = vacancyAndCourseRepository;
    }

    //Retorna estudantes matriculados em turmas que tem relação com o curso informado
    public List<Student> filterStudents(JobOffer jobOffer){
        //Retorna uma lista dos cursos selecionados pela empresa durante a oferta da vaga e a converte em uma lista de CourseSelect
        List<CourseSelect> courseSelects = vacancyAndCourseRepository.findByCoursesExistsInVacancyAndCourse(jobOffer.getId())
                .stream().map(CourseSelect::new)
                .toList();

        List<Student> students = new ArrayList<>();

        for (CourseSelect courseSelect : courseSelects){
            students.addAll(repository.findByStudentsExistsInCourse(courseSelect.courseId()));
        }

        return students;
    }
}
