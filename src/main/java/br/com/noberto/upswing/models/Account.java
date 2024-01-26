package br.com.noberto.upswing.models;

import br.com.noberto.upswing.dtos.student.RegisterStudent;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Embeddable
@NoArgsConstructor @Data
public class Account {

    private String name;
    private LocalDate birthDate;
    private String socialSecurity;
    private String mainPhone;
    private String optionalPhone;
    private String mail;
    private String password;
    private Boolean activeProfile;

    public Account(RegisterStudent student) {
        this.name = student.name();
        this.birthDate = student.birthDate();
        this.socialSecurity = student.socialSecurity();
        this.mainPhone = student.mainPhone();
        this.optionalPhone = student.optionalPhone();
        this.mail = student.mail();
        this.password = student.password();
        this.activeProfile = true;
    }
}
