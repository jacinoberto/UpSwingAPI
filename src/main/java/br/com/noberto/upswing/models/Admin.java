package br.com.noberto.upswing.models;

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
    private String occupation;

    @Embedded
    private Account account;
}
