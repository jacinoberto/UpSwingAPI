package br.com.noberto.upswing.models;

import br.com.noberto.upswing.enums.Contract;
import br.com.noberto.upswing.enums.Location;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_automatic_applications")
@AllArgsConstructor @NoArgsConstructor @Data
public class AutomaticApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Boolean enrolledCourse;
    private Contract contract;

    @Enumerated(EnumType.STRING)
    private Location location;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;
}
