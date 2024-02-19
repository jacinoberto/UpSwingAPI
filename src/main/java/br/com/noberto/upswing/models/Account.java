package br.com.noberto.upswing.models;

import br.com.noberto.upswing.dtos.admin.AdminUpdate;
import br.com.noberto.upswing.dtos.admin.RegisterAdmin;
import br.com.noberto.upswing.dtos.company.RegisterCompany;
import br.com.noberto.upswing.dtos.student.RegisterStudent;
import br.com.noberto.upswing.dtos.student.StudentResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor @Data
public class Account {

    private String name;

    @Column(name = "main_phone")
    private String mainPhone;

    @Column(name = "optional_phone")
    private String optionalPhone;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(name = "active_profile")
    private Boolean activeProfile;

    public Account(RegisterStudent student) {
        this.name = student.account().getName();
        this.mainPhone = student.account().getMainPhone();
        this.optionalPhone = student.account().getOptionalPhone();
        this.email = student.account().getEmail();
        this.password = student.account().getPassword();
        this.activeProfile = true;
    }

    public Account(StudentResponse student) {
        this.name = student.name();
        this.mainPhone = student.mainPhone();
        this.email = student.mail();
    }

    public Account(RegisterAdmin admin) {
        this.name = admin.account().getName();
        this.mainPhone = admin.account().getMainPhone();
        this.optionalPhone = admin.account().getOptionalPhone();
        this.email = admin.account().getEmail();
        this.password = admin.account().getPassword();
        this.activeProfile = true;
    }

    public Account(AdminUpdate admin) {
        this.name = admin.account().getName();
        this.mainPhone = admin.account().getMainPhone();
        this.optionalPhone = admin.account().getOptionalPhone();
        this.email = admin.account().getEmail();
    }

    public Account(RegisterCompany company) {
        this.name = company.account().getName();
        this.mainPhone = company.account().getMainPhone();
        this.optionalPhone = company.account().getOptionalPhone();
        this.email = company.account().getEmail();
        this.password = company.account().getPassword();
        this.activeProfile = true;
    }

    public Account(Admin admin) {
        this.name = admin.getAccount().getName();
        this.mainPhone = admin.getAccount().getMainPhone();
        this.optionalPhone = admin.getAccount().getOptionalPhone();
        this.email = admin.getAccount().getEmail();
        this.password = admin.getAccount().getPassword();
        this.activeProfile = true;
    }

    public Account(Student student) {
        this.name = student.getAccount().getName();
        this.mainPhone = student.getAccount().getMainPhone();
        this.optionalPhone = student.getAccount().getOptionalPhone();
        this.email = student.getAccount().getEmail();
        this.password = student.getAccount().getPassword();
        this.activeProfile = true;
    }

    public Account(Company company) {
        this.name = company.getAccount().getName();
        this.mainPhone = company.getAccount().getMainPhone();
        this.optionalPhone = company.getAccount().getOptionalPhone();
        this.email = company.getAccount().getEmail();
        this.password = company.getAccount().getPassword();
        this.activeProfile = true;
    }
}
