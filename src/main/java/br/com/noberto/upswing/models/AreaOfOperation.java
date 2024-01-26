package br.com.noberto.upswing.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_area_of_operations")
@AllArgsConstructor @NoArgsConstructor @Data
public class AreaOfOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_area_of_operation")
    private UUID id;
    private String area;
}
