package br.com.noberto.upswing.models;

import br.com.noberto.upswing.dtos.admin.AdminUpdate;
import br.com.noberto.upswing.dtos.admin.RegisterAdmin;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_admins")
@AllArgsConstructor @NoArgsConstructor @Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_admin")
    private UUID id;

    @Column(name = "admin_position")
    private String position;

    @Embedded
    private Account account;

    public Admin(RegisterAdmin admin) {
        this.position = admin.position();
        this.account = new Account(admin);
    }

    public Admin(AdminUpdate admin) {
        this.position = admin.position();
        this.account = new Account(admin);
    }

}
