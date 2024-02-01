package br.com.noberto.upswing.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_vacancy_offers")
@AllArgsConstructor @NoArgsConstructor @Data
public class VacancyOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_vacancy_offer")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_offer_id")
    private JobOffer jobOffer;
}