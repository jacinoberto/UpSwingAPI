package br.com.noberto.upswing.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_disciplines")
@AllArgsConstructor @NoArgsConstructor @Data
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_discipline")
    private UUID id;
    private String discipline;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;
}
