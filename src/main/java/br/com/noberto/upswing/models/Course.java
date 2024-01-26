package br.com.noberto.upswing.models;

import br.com.noberto.upswing.enums.EducationLevel;
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

    @Column(name = "education_level")
    @Enumerated(EnumType.STRING)
    private EducationLevel educationLevel;
    private Integer workload;

    @Column(name = "monthly_cost")
    private BigDecimal monthlyCost;

    @Column(name = "total_cost")
    private BigDecimal totalCost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "business_area_id")
    private BusinessArea businessArea;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private List<Subject> subjects = new ArrayList<>();

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private List<Course> courses = new ArrayList<>();
}
