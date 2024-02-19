package br.com.noberto.upswing.models;

import br.com.noberto.upswing.dtos.company.RegisterJobOffer;
import br.com.noberto.upswing.enums.Contract;
import br.com.noberto.upswing.enums.EducationLevel;
import br.com.noberto.upswing.enums.Mode;
import br.com.noberto.upswing.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

    @Enumerated(EnumType.STRING)
    Mode journey;

    @Column(name = "disable_person")
    private Boolean disablePerson;

    @Column(name = "offer_qty")
    private Integer offerQty;

    @Column(name = "work_schedule")
    private String workSchedule;

    @Column(name = "offer_description")
    private String offerDescription;

    @Column(name = "closing_date")
    private LocalDate closingDate;

    @Column(name = "benefits_mobility")
    private Boolean benefitsMobility;

    @Column(name = "benefits_education")
    private Boolean benefitsEducation;

    @Column(name = "benefits_health_wellness")
    private Boolean benefitsHealthWellness;

    @Column(name = "benefits_childcare")
    private Boolean benefitsChildcare;

    @Column(name = "benefits_meal")
    private Boolean benefitsMeal;

    @Column(name = "benefits_cultural")
    private Boolean benefitsCultural;

    @Enumerated(EnumType.STRING)
    private Status status;

    /*
        RELATIONSHIPS
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "business_area_id")
    private BusinessArea businessArea;

    @OneToMany(mappedBy = "jobOffer", fetch = FetchType.LAZY)
    private List<VacancyOffer> vacancyOffers = new ArrayList<>();

    @OneToMany(mappedBy = "jobOffer", fetch = FetchType.LAZY)
    private List<VacancyAndCourse> vacancyAndCourses = new ArrayList<>();

    @Transient
    private List<Course> courses = new ArrayList<>();

    public JobOffer(RegisterJobOffer jobOffer, Company company, BusinessArea businessArea) {
        this.position = jobOffer.position();
        this.educationLevel = EducationLevel.fromEducationLevel(jobOffer.educationLevel());
        this.contract = Contract.fromContract(jobOffer.contract());
        this.salary = jobOffer.salary();
        this.journey = Mode.fromLearningMode(jobOffer.journey());
        this.workSchedule = jobOffer.workSchedule();
        this.disablePerson = jobOffer.disablePerson();
        this.offerQty = jobOffer.offerQty();
        this.closingDate = jobOffer.closingDate();
        this.offerDescription = jobOffer.offerDescription();
        this.benefitsMobility = jobOffer.benefitsMobility();
        this.benefitsEducation = jobOffer.benefitsEducation();
        this.benefitsHealthWellness = jobOffer.benefitsHealthWellness();
        this.benefitsChildcare = jobOffer.benefitsChildcare();
        this.benefitsMeal = jobOffer.benefitsMeal();
        this.benefitsCultural = jobOffer.benefitsCultural();
        this.status = Status.PENDING;
        this.company = company;
        this.businessArea = businessArea;
    }
}
