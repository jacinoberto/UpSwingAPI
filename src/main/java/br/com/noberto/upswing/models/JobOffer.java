package br.com.noberto.upswing.models;

import br.com.noberto.upswing.dtos.company.RegisterJobOffer;
import br.com.noberto.upswing.enums.Contract;
import br.com.noberto.upswing.enums.EducationLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_job_offers")
@AllArgsConstructor @NoArgsConstructor @Data
public class JobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_job_offer")
    private UUID id;

    @Column(name = "job_position")
    private String position;

    @Enumerated(EnumType.STRING)
    @Column(name = "education_level")
    private EducationLevel educationLevel;

    @Enumerated(EnumType.STRING)
    private Contract contract;

    private BigDecimal salary;

    @Column(name = "disable_person")
    private Boolean disablePerson;

    @Column(name = "offer_number")
    private Integer offerNumber;

    @Column(name = "work_schedule")
    private String workSchedule;

    @Column(name = "assigned_functions")
    private String assignedFunctions;

    @Column(name = "offer_description")
    private String offerDescription;

    private LocalDate deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "business_area_id")
    private BusinessArea businessArea;

    public JobOffer(RegisterJobOffer jobOffer) {
        this.position = jobOffer.position();
        this.educationLevel = EducationLevel.fromEducationLevel(jobOffer.educationLevel());
        this.contract = Contract.fromContract(jobOffer.contract());
        this.salary = jobOffer.salary();
        this.workSchedule = jobOffer.workSchedule();
        this.disablePerson = jobOffer.disablePerson();
        this.offerNumber = jobOffer.offerNumber();
        this.deadline = jobOffer.deadline();
        this.offerDescription = jobOffer.offerDescription();
        this.assignedFunctions = jobOffer.offerDescription();
    }
}
