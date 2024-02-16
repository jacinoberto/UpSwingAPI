package br.com.noberto.upswing.models;

import br.com.noberto.upswing.dtos.student.RegisterStudent;
import br.com.noberto.upswing.dtos.student.StudentResponse;
import br.com.noberto.upswing.email.EmailSender;
import br.com.noberto.upswing.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_students")
@AllArgsConstructor @NoArgsConstructor @Data
public class Student implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_student")
    private String id;

    private String occupation;

    @Column(name = "social_security")
    private String socialSecurity;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Embedded
    private Account account;

    @Embedded
    private SocialNetworks socialNetworks;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "student")
    private List<AutoApply> autoApplies = new ArrayList<>();

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Registration> registrations = new ArrayList<>();

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<VacancyOffer> vacancyOffers = new ArrayList<>();

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<EmailSender> emails = new ArrayList<>();


    public Student(RegisterStudent student) {
        this.socialSecurity = student.socialSecurity();
        this.birthDate = student.birthDate();
        this.occupation = getOccupation();
        this.account = new Account(student);
        this.role = UserRole.STUDENT;
    }

    public Student(StudentResponse student) {
        this.occupation = student.occupation();
        this.account = new Account(student);
        this.socialNetworks = (student.socialNetworks() != null) ? student.socialNetworks() : new NullSocialNetworks();
        this.address = new Address(student.address(), new ZipCode(student.address().zipCode()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.STUDENT) return List.of(new SimpleGrantedAuthority("ROLE_STUDENT"));
        else return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_COMPANY"));
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
