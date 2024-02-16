package br.com.noberto.upswing.models;

import br.com.noberto.upswing.dtos.admin.AdminUpdate;
import br.com.noberto.upswing.dtos.admin.RegisterAdmin;
import br.com.noberto.upswing.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_admins")
@NoArgsConstructor @AllArgsConstructor @Data
public class Admin implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_admin")
    private String id;

    @Column(name = "admin_position")
    private String position;

    @Embedded
    private Account account;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public Admin(RegisterAdmin admin) {
        this.position = admin.position();
        this.account = new Account(admin);
        this.role = UserRole.ADMIN;
    }

    public Admin(AdminUpdate admin) {
        this.position = admin.position();
        this.account = new Account(admin);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_STUDENT"), new SimpleGrantedAuthority("ROLE_COMPANY"));
        else return List.of(new SimpleGrantedAuthority("ROLE_STUDENT"), new SimpleGrantedAuthority("ROLE_COMPANY"));
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
