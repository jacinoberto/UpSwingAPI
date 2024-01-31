package br.com.noberto.upswing.dtos.company;

import br.com.noberto.upswing.models.JobOffer;

import java.math.BigDecimal;
import java.time.LocalDate;

public record JobOfferResponse (
        String position,
        String name,
        BigDecimal salary,
        LocalDate closingDate
){
    public JobOfferResponse (JobOffer jobOffer){
        this(
                jobOffer.getPosition(),
                jobOffer.getCompany().getAccount().getName(),
                jobOffer.getSalary(),
                jobOffer.getClosingDate()
        );
    }
}
