package br.com.noberto.upswing.dtos.academic;

import br.com.noberto.upswing.dtos.area.BusinessAreaResponse;
import br.com.noberto.upswing.enums.EducationLevel;
import br.com.noberto.upswing.models.Course;

import java.util.List;
import java.util.stream.Collectors;

public record CourseResponseAdmin(
        String courseName,
        BusinessAreaResponse businessArea,
        String educationLevel,
        Integer schedule,
        List<SubjectResponse> subjects
) {
    public CourseResponseAdmin (Course course){
        this(
                course.getCourseName(),
                new BusinessAreaResponse(course.getBusinessArea()),
                EducationLevel.fromString(course.getEducationLevel()),
                course.getSchedule(),
                course.getSubjects().stream()
                        .map(SubjectResponse::new).toList()
        );
    }
}
