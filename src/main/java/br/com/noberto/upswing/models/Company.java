package br.com.noberto.upswing.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_companies")
@AllArgsConstructor @NoArgsConstructor @Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_company")
    private UUID id;

    @Column(name = "corporate_name")
    private String corporateName;

    @Column(name = "fantasy_name")
    private String fantasyName;
    private String cnpj;
    private String description;
    private String site;

    @Column(name = "main_phone")
    private String mainPhone;

    @Column(name = "optional_phone")
    private String optionalPhone;

    @Embedded
    private SocialNetworks socialNetworks;
    private String email;
    private String password;

    @Column(name = "active_profile")
    private Boolean activeProfile;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "area_of_operation_id")
    private AreaOfOperation areaOfOperation;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
}
