package br.com.noberto.upswing.dtos.company;

import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.models.VacancyOffer;

import java.util.UUID;

public record VacancyOfferResponse(
        UUID id,
        String name,
        String position,
        Boolean status
) {
    public VacancyOfferResponse(VacancyOffer vacancyOffer) {
        this(
                vacancyOffer.getId(),
                vacancyOffer.getStudent().getAccount().getName(),
                vacancyOffer.getJobOffer().getPosition(),
                vacancyOffer.getStatus()
        );
    }
}
