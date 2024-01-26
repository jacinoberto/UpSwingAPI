package br.com.noberto.upswing.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class SocialNetworks {
    private String socialOne;
    private String socialTwo;
    private String socialThree;
    private String socialFour;

}
