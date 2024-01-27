package br.com.noberto.upswing.models;

import br.com.noberto.upswing.dtos.company.RegisterCompany;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor @NoArgsConstructor @Data
public class SocialNetworks {
    private String socialOne;
    private String socialTwo;
    private String socialThree;
    private String socialFour;
}
