package br.com.noberto.upswing.models;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
public class Account {

    private String name;
    private LocalDate dateOfBirth;
    private String cpf;
    private String mainPhone;
    private String optionalPhone;
    private String email;
    private String password;
    private Boolean activeProfile;
}
