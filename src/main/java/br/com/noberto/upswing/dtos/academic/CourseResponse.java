package br.com.noberto.upswing.dtos.academic;

import br.com.noberto.upswing.enums.EducationLevel;
import br.com.noberto.upswing.models.Course;

import java.util.List;

public record CourseResponse (
        String courseName,
        String educationalLevel,
        Integer schedule,
        List<SubjectResponse> subjects
){
    public CourseResponse (Course course){
        this(
                course.getCourseName(),
                EducationLevel.fromString(course.getEducationLevel()),
                course.getSchedule(),
                course.getSubjects().stream()
                        .map(SubjectResponse::new)
                        .toList()
        );
    }
}
