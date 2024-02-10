package br.com.noberto.upswing.dtos.academic;

import br.com.noberto.upswing.dtos.area.BusinessAreaRequest;
import br.com.noberto.upswing.enums.EducationLevel;
import br.com.noberto.upswing.models.Course;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CourseRequest(
        UUID id,
        @NotBlank
        String courseName,
        UUID businessAreaId,
        @NotBlank
        String educationalLevel,
        @NotNull
        Integer schedule,
        BigDecimal monthlyCost,
        BigDecimal totalCost
) {
        public CourseRequest(Course course) {
               this(
                       course.getId(),
                        course.getCourseName(),
                        course.getBusinessArea().getId(),
                       EducationLevel.fromString(course.getEducationLevel()),
                        course.getSchedule(),
                        course.getMonthlyCost(),
                        course.getTotalCost()
               );
        }
}
