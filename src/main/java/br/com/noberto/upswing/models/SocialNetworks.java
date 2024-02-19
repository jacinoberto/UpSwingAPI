package br.com.noberto.upswing.models;

import br.com.noberto.upswing.dtos.company.RegisterCompany;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor @NoArgsConstructor @Data
public class SocialNetworks {

    @Column(name = "social_one")
    private String socialOne;

    @Column(name = "social_two")
    private String socialTwo;

    @Column(name = "social_three")
    private String socialThree;

    @Column(name = "social_four")
    private String socialFour;

    public SocialNetworks(Student student) {
        this.socialOne = student.getSocialNetworks().getSocialOne();
        this.socialTwo = student.getSocialNetworks().getSocialTwo();
        this.socialThree = student.getSocialNetworks().getSocialThree();
        this.socialFour = student.getSocialNetworks().getSocialFour();
    }
}
