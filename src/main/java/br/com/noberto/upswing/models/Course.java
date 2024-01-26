package br.com.noberto.upswing.models;

import br.com.noberto.upswing.enums.EducationDegree;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_courses")
@AllArgsConstructor @NoArgsConstructor @Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_course")
    private UUID id;
    private String course;

    @Column(name = "education_degree")
    @Enumerated(EnumType.STRING)
    private EducationDegree educationDegree;
    private Integer workload;

    @Column(name = "monthly_value")
    private BigDecimal monthlyValue;

    @Column(name = "total_value")
    private BigDecimal totalValue;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "area_of_operation_id")
    private AreaOfOperation areaOfOperation;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private List<Discipline> disciplines = new ArrayList<>();

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private List<Course> courses = new ArrayList<>();
}
