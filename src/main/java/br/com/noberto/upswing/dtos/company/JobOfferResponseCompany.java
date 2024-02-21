package br.com.noberto.upswing.dtos.company;

import br.com.noberto.upswing.dtos.address.AddressRequest;
import br.com.noberto.upswing.enums.Contract;
import br.com.noberto.upswing.enums.EducationLevel;
import br.com.noberto.upswing.models.JobOffer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record JobOfferResponseCompany(
        UUID jobOfferId,
        String name,
        String businessArea,
        String educationLevel,
        String position,
        BigDecimal salary,
        AddressRequest address,
        String contract,
        Boolean benefitsMeal,
        Boolean benefitsMobility,
        Boolean benefitsCultural,
        Boolean benefitsEducation,
        Boolean benefitsHealthInsurance,
        Integer offerQty,
        LocalDate closingDate,
        String offerDescription
) {
    public JobOfferResponseCompany (JobOffer jobOffer){
        this(
                jobOffer.getId(),
                jobOffer.getCompany().getAccount().getName(),
                jobOffer.getBusinessArea().getBusinessArea(),
                EducationLevel.fromString(jobOffer.getEducationLevel()),
                jobOffer.getPosition(),
                jobOffer.getSalary(),
                new AddressRequest(jobOffer.getCompany().getAddress()),
                Contract.fromString(jobOffer.getContract()),
                jobOffer.getBenefitsMeal(),
                jobOffer.getBenefitsMobility(),
                jobOffer.getBenefitsCultural(),
                jobOffer.getBenefitsEducation(),
                jobOffer.getBenefitsHealthWellness(),
                jobOffer.getOfferQty(),
                jobOffer.getClosingDate(),
                jobOffer.getOfferDescription()
        );
    }
}
