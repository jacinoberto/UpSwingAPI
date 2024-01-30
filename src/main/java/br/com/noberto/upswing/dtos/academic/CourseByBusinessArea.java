package br.com.noberto.upswing.dtos.academic;

import br.com.noberto.upswing.enums.EducationLevel;
import br.com.noberto.upswing.models.Course;

import java.util.List;

public record CourseByBusinessArea(
        String area,
        String educationalLevel,
        String courseName,
        Integer schedule,
        List<SubjectResponse> subjects
){
    public CourseByBusinessArea(Course course){
        this(
                course.getBusinessArea().getBusinessArea(),
                EducationLevel.fromString(course.getEducationLevel()),
                course.getCourseName(),
                course.getSchedule(),
                course.getSubjects().stream()
                        .map(SubjectResponse::new)
                        .toList()
        );
    }
}
