package br.com.noberto.upswing.models;

import br.com.noberto.upswing.dtos.company.RegisterCompany;
import br.com.noberto.upswing.enums.Status;
import br.com.noberto.upswing.email.EmailSender;
import br.com.noberto.upswing.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_companies")
@AllArgsConstructor @NoArgsConstructor @Data
public class Company implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_company")
    private String id;

    @Embedded
    private Account account;

    @Column(name = "trading_name")
    private String tradingName;

    @Column(name = "company_code", unique = true)
    private String companyCode;
    private String description;
    private String website;

    @Embedded
    private SocialNetworks socialNetworks;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "business_area_id")
    private BusinessArea businessArea;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<EmailSender> emails = new ArrayList<>();

    public Company(RegisterCompany company) {
        this.account = new Account(company);
        this.tradingName = company.companyName();
        this.companyCode = company.companyCode();
        this.description = company.description();
        this.website = company.website();
        this.status = Status.PENDING;
        this.role = UserRole.COMPANY;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (this.role == UserRole.COMPANY) {
            authorities.add(new SimpleGrantedAuthority("ROLE_COMPANY"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
        }

        return authorities;
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
