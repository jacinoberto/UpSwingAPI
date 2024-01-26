package br.com.noberto.upswing.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_addresses")
@AllArgsConstructor @NoArgsConstructor @Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_address")
    private UUID id;
    private Integer number;
    private String complement;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "zip_code_id")
    private ZipCode zipCode;

    @OneToMany(mappedBy = "address")
    private List<Company> companies = new ArrayList<>();
}
