package br.com.noberto.upswing.dtos.company;

import br.com.noberto.upswing.enums.Contract;
import br.com.noberto.upswing.enums.EducationLevel;
import br.com.noberto.upswing.models.JobOffer;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record JobOfferResponse (
        String name,
        String educationLevel,
        String position,
        String contract,
        Boolean benefitsMealVoucher ,
        Boolean benefitsMobility,
        Boolean benefitsCulture,
        Boolean benefitsEducation,
        Boolean benefitsHealthInsurance,
        Integer offerQty,
        BigDecimal salary,
        LocalDate closingDate,
        String offerDescription
){
    public JobOfferResponse (JobOffer jobOffer){
        this(
                jobOffer.getCompany().getAccount().getName(),
                EducationLevel.fromString(jobOffer.getEducationLevel()),
                jobOffer.getPosition(),
                Contract.fromString(jobOffer.getContract()),
                jobOffer.getBenefitsMeal(),
                jobOffer.getBenefitsMobility(),
                jobOffer.getBenefitsCultural(),
                jobOffer.getBenefitsEducation(),
                jobOffer.getBenefitsHealthWellness(),
                jobOffer.getOfferQty(),
                jobOffer.getSalary(),
                jobOffer.getClosingDate(),
                jobOffer.getOfferDescription()
        );
    }
}
