package br.com.noberto.upswing.dtos.company;

import br.com.noberto.upswing.enums.Contract;
import br.com.noberto.upswing.enums.EducationLevel;
import br.com.noberto.upswing.models.JobOffer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record RegisterJobOffer(
        @NotNull
        UUID companyId,
        @NotNull
        UUID businessAreaId,
        @NotBlank
        String position,
        @NotBlank
        String educationLevel,
        @NotBlank
        String contract,
        @NotBlank
        String salary,
        Boolean disablePerson,
        @NotNull
        Integer offerNumber,
        @NotBlank
        String workSchedule,
        String offerDescription,
        @NotNull
        LocalDate deadline

) {
    public RegisterJobOffer(JobOffer jobOffer) {
        this(
                jobOffer.getCompany().getId(),
                jobOffer.getBusinessArea().getId(),
                jobOffer.getPosition(),
                EducationLevel.fromString(jobOffer.getEducationLevel()),
                Contract.fromString(jobOffer.getContract()),
                jobOffer.getSalary().toString(),
                jobOffer.getDisablePerson(),
                jobOffer.getOfferNumber(),
                jobOffer.getWorkSchedule(),
                jobOffer.getOfferDescription(),
                jobOffer.getDeadline()
        );
    }
}
