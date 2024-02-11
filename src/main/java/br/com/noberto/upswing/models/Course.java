package br.com.noberto.upswing.models;

import br.com.noberto.upswing.dtos.academic.CourseRequest;
import br.com.noberto.upswing.dtos.academic.CourseByBusinessArea;
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
    private EducationLevel educationLevel;

    private Integer schedule;

    @Column(name = "monthly_cost")
    private BigDecimal monthlyCost;

    @Column(name = "total_cost")
    private BigDecimal totalCost;

    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_area_id")
    private BusinessArea businessArea;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Subject> subjects = new ArrayList<>();

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Class> classes = new ArrayList<>();

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<VacancyAndCourse> vacancyAndCourses = new ArrayList<>();

    public Course(CourseRequest course) {
        this.courseName = course.courseName();
        this.educationLevel = EducationLevel.fromEducationLevel(course.educationalLevel());
        this.schedule = course.schedule();
        this.monthlyCost = course.monthlyCost();
        this.totalCost = course.totalCost();
        this.active = true;
    }

    public Course(CourseByBusinessArea course) {
        this.courseName = course.courseName();
        this.educationLevel = EducationLevel.valueOf(course.educationalLevel());
        this.schedule = course.schedule();
        this.subjects = course.subjects().stream()
                .map(Subject::new).toList();
    }
}
