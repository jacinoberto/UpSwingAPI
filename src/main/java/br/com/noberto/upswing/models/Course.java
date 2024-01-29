package br.com.noberto.upswing.models;

import br.com.noberto.upswing.dtos.academic.CourseRequest;
import br.com.noberto.upswing.dtos.academic.CourseResponse;
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

    @Column(name = "course_name")
    private String courseName;

    @Enumerated(EnumType.STRING)
    private EducationLevel degree;

    private Integer schedule;

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
    private List<Class> classes = new ArrayList<>();

    public Course(CourseRequest course) {
        this.courseName = course.courseName();
        this.degree = EducationLevel.fromEducationLevel(course.degree());
        this.schedule = course.schedule();
        this.monthlyCost = course.monthlyCost();
        this.totalCost = course.totalCost();
    }

    public Course(CourseResponse course) {
        this.courseName = course.courseName();
        this.degree = EducationLevel.valueOf(course.degree());
        this.schedule = course.schedule();
        this.subjects = course.subjects().stream()
                .map(Subject::new).toList();
    }
}
