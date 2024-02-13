package br.com.noberto.upswing.models;

import br.com.noberto.upswing.dtos.academic.CompletedSubjectRequest;
import br.com.noberto.upswing.repositories.CompletedSubjectRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_completed_subjects")
@AllArgsConstructor @NoArgsConstructor @Data
public class CompletedSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_completed_subject")
    private UUID id;

    private Boolean complete;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class aClass;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
}
