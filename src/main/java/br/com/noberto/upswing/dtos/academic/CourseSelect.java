package br.com.noberto.upswing.dtos.academic;

import br.com.noberto.upswing.models.Course;

import java.util.UUID;

public record CourseSelect(
        UUID courseId,
        String courseName
) {
    public CourseSelect (Course course){
        this(
                course.getId(),
                course.getCourseName()
        );
    }
}
