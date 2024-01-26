package br.com.noberto.upswing.models;

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
@Table(name = "tb_job_vacancies")
@AllArgsConstructor @NoArgsConstructor @Data
public class JobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_job_vacancy")
    private UUID id;
    private String position;

    @Enumerated(EnumType.STRING)
    private EducationLevel educationLevel;

    @Enumerated(EnumType.STRING)
    private Contract contract;
    private BigDecimal salary;

    @Column(name = "disabled_person")
    private Boolean disability;

    @Column(name = "number_of_vacancies")
    private Integer vacanciesNumber;
    private LocalDate deadline;
    private String description;

    @Column(name = "assigned_functions")
    private String assignedFunctions;
    private Boolean benefitsMealVoucher;

    @Column(name = "benefits_food_voucher")
    private Boolean benefitsFoodVoucher;

    @Column(name = "benefits_transport_allowance")
    private Boolean benefitsTransportAllowance;

    @Column(name = "benefits_culture")
    private Boolean benefitsCulture;

    @Column(name = "benefits_education")
    private Boolean benefitsEducation;

    @Column(name = "benefits_health_insurance")
    private Boolean benefitsHealthInsurance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "area_of_operation_id")
    private BusinessArea businessArea;
}
