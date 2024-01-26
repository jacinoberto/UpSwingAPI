package br.com.noberto.upswing.models;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
public class Account {

    private String name;
    private LocalDate birthDate;
    private String socialSecurity;
    private String mainPhone;
    private String optionalPhone;
    private String mail;
    private String password;
    private Boolean activeProfile;
}
