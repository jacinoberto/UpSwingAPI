package br.com.noberto.upswing.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_business_areas")
@AllArgsConstructor @NoArgsConstructor @Data
public class BusinessArea {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_area_of_operation")
    private UUID id;
    private String area;

    @OneToMany(mappedBy = "areaOfOperation")
    private List<Company> companies = new ArrayList<>();

    @OneToMany(mappedBy = "areaOfOperation")
    private List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "areaOfOperation")
    private List<JobOffer> jobVacancies = new ArrayList<>();
}
