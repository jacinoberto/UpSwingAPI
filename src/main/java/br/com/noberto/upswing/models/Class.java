package br.com.noberto.upswing.models;

import br.com.noberto.upswing.dtos.academic.ClassRequest;
import br.com.noberto.upswing.enums.Mode;
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
    private Mode mode;

    @Enumerated(EnumType.STRING)
    private Shift shift;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "closing_date")
    private LocalDate closingDate;

    @Column(name = "vacancy_number")
    private Integer vacancyNumber;

    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "aClass", fetch = FetchType.LAZY)
    private List<Registration> registrations = new ArrayList<>();

    @OneToMany(mappedBy = "aClass", fetch = FetchType.LAZY)
    private List<CompletedSubject> completedSubjects = new ArrayList<>();

    public Class(ClassRequest classRequest, Integer code, Course course) {
        this.code = code;
        this.mode = Mode.fromLearningMode(classRequest.mode());
        this.shift = Shift.fromShift(classRequest.shift());
        this.startDate = classRequest.startDate();
        this.closingDate = classRequest.closingDate();
        this.vacancyNumber = classRequest.vacancyNumber();
        this.course = course;
        this.active = true;
    }
}
