package br.com.noberto.upswing.models;

import br.com.noberto.upswing.dtos.company.RegisterCompany;
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

    @Embedded
    private Account account;

    @Column(name = "trading_name")
    private String tradingName;

    @Column(name = "companyCode", unique = true)
    private String companyCode;
    private String description;
    private String website;

    @Embedded
    private SocialNetworks socialNetworks;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "business_area_id")
    private BusinessArea businessArea;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    public Company(RegisterCompany company) {
        this.account = new Account(company);
        this.tradingName = company.tradingName();
        this.companyCode = company.companyCode();
        this.description = company.description();
        this.website = company.website();
    }
}
