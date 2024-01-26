package br.com.noberto.upswing.models;

import br.com.noberto.upswing.enums.Contract;
import br.com.noberto.upswing.enums.EducationDegree;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class JobVacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_job_vacancy")
    private UUID id;
    private String occupation;

    @Enumerated(EnumType.STRING)
    private EducationDegree degree;

    @Enumerated(EnumType.STRING)
    private Contract contract;
    private BigDecimal remuneration;

    @Column(name = "disabled_person")
    private Boolean disabledPerson;

    @Column(name = "number_of_vacancies")
    private Integer numberOfVacancies;
    private LocalDate deadline;
    private String description;

    @Column(name = "assigned_functions")
    private String assignedFunctions;
    private Boolean meal;

    @Column(name = "food_voucher")
    private Boolean foodVoucher;

    @Column(name = "transportation_allowance")
    private Boolean transportationAllowance;
    private Boolean culture;
    private Boolean aducation;

    @Column(name = "health_plan")
    private Boolean healthPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "area_of_operation_id")
    private AreaOfOperation areaOfOperation;
}
