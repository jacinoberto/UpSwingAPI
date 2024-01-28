package br.com.noberto.upswing.dtos.academic;

import br.com.noberto.upswing.dtos.area.BusinessAreaRequest;
import br.com.noberto.upswing.enums.EducationLevel;
import br.com.noberto.upswing.models.Course;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CourseRequest(
        @NotBlank
        String courseName,
        UUID businessAreaId,
        @NotBlank
        String degree,
        @NotNull
        Integer schedule,
        BigDecimal monthlyCost,
        BigDecimal totalCost
) {
        public CourseRequest(Course course) {
               this(
                        course.getCourseName(),
                        course.getBusinessArea().getId(),
                       EducationLevel.fromString(course.getDegree()),
                        course.getSchedule(),
                        course.getMonthlyCost(),
                        course.getTotalCost()
               );
        }
}
