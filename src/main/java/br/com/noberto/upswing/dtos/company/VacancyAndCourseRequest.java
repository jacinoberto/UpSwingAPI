package br.com.noberto.upswing.dtos.company;

import br.com.noberto.upswing.models.VacancyAndCourse;

import java.util.UUID;

public record VacancyAndCourseRequest(
        UUID jobOffer,
        UUID course
) {
    public VacancyAndCourseRequest (VacancyAndCourse vacancyAndCourse){
        this(
                vacancyAndCourse.getJobOffer().getId(),
                vacancyAndCourse.getCourse().getId()
        );
    }
}
