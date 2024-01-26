package br.com.noberto.upswing.models;

import br.com.noberto.upswing.enums.LearningMode;
import br.com.noberto.upswing.enums.Shift;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_classes")
@AllArgsConstructor @NoArgsConstructor @Data
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_class")
    private UUID id;
    private Integer code;

    @Enumerated(EnumType.STRING)
    private LearningMode learningMode;

    @Enumerated(EnumType.STRING)
    private Shift shift;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "final_date")
    private LocalDate finalDate;

    @Column(name = "number_of_vacancies")
    private Integer vacanciesNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "aClass", fetch = FetchType.LAZY)
    private List<Registration> matriculations = new ArrayList<>();
}
