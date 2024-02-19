package br.com.noberto.upswing.dtos.company;

import br.com.noberto.upswing.dtos.address.AddressRequest;
import br.com.noberto.upswing.enums.Contract;
import br.com.noberto.upswing.enums.EducationLevel;
import br.com.noberto.upswing.models.JobOffer;

import java.math.BigDecimal;
import java.time.LocalDate;

public record JobOfferResponseCompany(
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
    public JobOfferResponseCompany (JobOffer JobOffer){
        this(
                JobOffer.getCompany().getAccount().getName(),
                JobOffer.getBusinessArea().getBusinessArea(),
                EducationLevel.fromString(JobOffer.getEducationLevel()),
                JobOffer.getPosition(),
                JobOffer.getSalary(),
                new AddressRequest(JobOffer.getCompany().getAddress()),
                Contract.fromString(JobOffer.getContract()),
                JobOffer.getBenefitsMeal(),
                JobOffer.getBenefitsMobility(),
                JobOffer.getBenefitsCultural(),
                JobOffer.getBenefitsEducation(),
                JobOffer.getBenefitsHealthWellness(),
                JobOffer.getOfferQty(),
                JobOffer.getClosingDate(),
                JobOffer.getOfferDescription()
        );
    }
}
