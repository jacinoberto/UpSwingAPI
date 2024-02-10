package br.com.noberto.upswing.dtos.academic;

import br.com.noberto.upswing.dtos.area.BusinessAreaResponse;
import br.com.noberto.upswing.enums.EducationLevel;
import br.com.noberto.upswing.models.Course;
import br.com.noberto.upswing.models.Subject;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record CourseResponseAdmin(
        UUID courseId,
        String courseName,
        String businessArea,
        String educationLevel,
        Integer schedule,
        List<String> subjects
) {
    public CourseResponseAdmin (Course course){
        this(
                course.getId(),
                course.getCourseName(),
                course.getBusinessArea().getBusinessArea(),
                EducationLevel.fromString(course.getEducationLevel()),
                course.getSchedule(),
                course.getSubjects().stream()
                        .map(Subject::getSubjectName).toList()
        );
    }
}
