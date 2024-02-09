package br.com.noberto.upswing.util.filters;

import br.com.noberto.upswing.dtos.academic.CourseSelect;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FilterExistingStudentsOnCourse{
    private static StudentRepository repository;

    @Autowired
    public FilterExistingStudentsOnCourse(StudentRepository repository) {
        FilterExistingStudentsOnCourse.repository = repository;
    }

    //Retorna estudantes matriculados em turmas que tem relação com o curso informado
    public static List<Student> returnStudentsExistsInCourse(List<CourseSelect> courseSelects){
        List<Student> students = new ArrayList<>();

        for (CourseSelect courseSelect : courseSelects){
            students = repository.findByStudentsExistsInCourse(courseSelect.courseId());
        }

        return students;
    }
}
