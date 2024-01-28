package br.com.noberto.upswing.models;

import br.com.noberto.upswing.dtos.address.AddressRequest;
import br.com.noberto.upswing.dtos.student.RegisterStudent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_students")
@AllArgsConstructor @NoArgsConstructor @Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_student")
    private UUID id;

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

    @OneToMany(mappedBy = "student")
    private List<AutoApply> autoApplies = new ArrayList<>();

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Registration> registrations = new ArrayList<>();

    public Student(RegisterStudent student) {
        this.socialSecurity = student.socialSecurity();
        this.birthDate = student.birthDate();
        this.occupation = getOccupation();
        this.account = new Account(student);
    }
}
