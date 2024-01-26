package br.com.noberto.upswing.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_matriculations")
@AllArgsConstructor @NoArgsConstructor @Data
public class Matriculation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_matriculation")
    private UUID id;
    private Integer code;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    private  Class aClass;
}
