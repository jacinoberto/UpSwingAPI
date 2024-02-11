package br.com.noberto.upswing.dtos.academic;

import br.com.noberto.upswing.enums.EducationLevel;
import br.com.noberto.upswing.models.Course;

import java.util.List;
import java.util.UUID;

public record CourseResponse (
        UUID courseId,
        String courseName,
        String educationalLevel,
        Integer schedule,
        List<SubjectResponse> subjects
){
    public CourseResponse (Course course){
        this(
                course.getId(),
                course.getCourseName(),
                EducationLevel.fromString(course.getEducationLevel()),
                course.getSchedule(),
                course.getSubjects().stream()
                        .map(SubjectResponse::new)
                        .toList()
        );
    }
}
