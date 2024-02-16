package br.com.noberto.upswing.dtos.company;

import br.com.noberto.upswing.dtos.academic.CourseSelect;
import br.com.noberto.upswing.enums.Contract;
import br.com.noberto.upswing.enums.EducationLevel;
import br.com.noberto.upswing.enums.Mode;
import br.com.noberto.upswing.enums.Status;
import br.com.noberto.upswing.models.Course;
import br.com.noberto.upswing.models.JobOffer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record RegisterJobOffer(
        @NotNull
        String companyId,
        @NotBlank
        String position,
        @NotNull
        UUID businessAreaId,
        List<CourseSelect> courses,
        @NotBlank
        String educationLevel,
        @NotBlank
        String contract,
        @NotNull
        BigDecimal salary,
        @NotBlank
        String journey,
        @NotNull
        Boolean disablePerson,
        @NotNull
        Integer offerQty,
        @NotBlank
        String workSchedule,
        @NotBlank
        String offerDescription,
        @NotNull
        LocalDate closingDate,
        @NotNull
        Boolean benefitsMobility,
        @NotNull
        Boolean benefitsEducation,
        @NotNull
        Boolean benefitsHealthWellness,
        @NotNull
        Boolean benefitsChildcare,
        @NotNull
        Boolean benefitsMeal,
        @NotNull
        Boolean benefitsCultural

) {
    public RegisterJobOffer(JobOffer jobOffer) {
        this(
                jobOffer.getCompany().getId(),
                jobOffer.getPosition(),
                jobOffer.getBusinessArea().getId(),
                new ArrayList<>(),
                EducationLevel.fromString(jobOffer.getEducationLevel()),
                Contract.fromString(jobOffer.getContract()),
                jobOffer.getSalary(),
                Mode.fromString(jobOffer.getJourney()),
                jobOffer.getDisablePerson(),
                jobOffer.getOfferQty(),
                jobOffer.getWorkSchedule(),
                jobOffer.getOfferDescription(),
                jobOffer.getClosingDate(),
                jobOffer.getBenefitsMobility(),
                jobOffer.getBenefitsEducation(),
                jobOffer.getBenefitsHealthWellness(),
                jobOffer.getBenefitsChildcare(),
                jobOffer.getBenefitsMeal(),
                jobOffer.getBenefitsCultural()
        );
    }
}
