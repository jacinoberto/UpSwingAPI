package br.com.noberto.upswing.models;

import br.com.noberto.upswing.dtos.admin.RegisterAdmin;
import br.com.noberto.upswing.dtos.student.RegisterStudent;
import jakarta.persistence.Column;
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
    @Column(unique = true)
    private String socialSecurity;
    private String mainPhone;
    private String optionalPhone;
    @Column(unique = true)
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

    public Account(RegisterAdmin admin) {
        this.name = admin.name();
        this.birthDate = admin.birthDate();
        this.socialSecurity = admin.socialSecurity();
        this.mainPhone = admin.mainPhone();
        this.optionalPhone = admin.optionalPhone();
        this.mail = admin.mail();
        this.password = admin.password();
        this.activeProfile = true;
    }

    public Account(Admin admin) {
        this.name = admin.getAccount().getName();
        this.birthDate = admin.getAccount().getBirthDate();
        this.socialSecurity = admin.getAccount().getSocialSecurity();
        this.mainPhone = admin.getAccount().getMainPhone();
        this.optionalPhone = admin.getAccount().getOptionalPhone();
        this.mail = admin.getAccount().getMail();
    }
}
