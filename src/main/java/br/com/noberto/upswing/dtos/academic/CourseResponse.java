package br.com.noberto.upswing.dtos.academic;

import br.com.noberto.upswing.enums.EducationLevel;
import br.com.noberto.upswing.models.BusinessArea;
import br.com.noberto.upswing.models.Course;
import br.com.noberto.upswing.models.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record CourseResponse (
        String area,
        String educationalLevel,
        String courseName,
        Integer schedule,
        List<SubjectResponse> subjects
){
    public CourseResponse (Course course){
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
