package br.com.noberto.upswing.dtos.academic;

import br.com.noberto.upswing.enums.EducationLevel;
import br.com.noberto.upswing.models.Course;

import java.util.List;
import java.util.UUID;

public record CourseByBusinessArea(
        UUID courseId,
        String area,
        String educationalLevel,
        String courseName,
        Integer schedule,
        List<SubjectResponse> subjects
){
    public CourseByBusinessArea(Course course){
        this(
                course.getId(),
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
