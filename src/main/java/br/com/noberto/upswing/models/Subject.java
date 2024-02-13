package br.com.noberto.upswing.models;

import br.com.noberto.upswing.dtos.academic.SubjectRequest;
import br.com.noberto.upswing.dtos.academic.SubjectResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_subjects")
@AllArgsConstructor @NoArgsConstructor @Data
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_subject")
    private UUID id;

    @Column(name = "subject_name")
    private String subjectName;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    private List<CompletedSubject> completedSubjects = new ArrayList<>();

    public Subject(SubjectRequest subject, Course course) {
        this.subjectName = subject.subjectName();
        this.description = subject.description();
        this.course = course;
    }

    public Subject(SubjectResponse subject) {
        this.subjectName = subject.subjectName();
    }
}
