package br.com.noberto.upswing.models;

import br.com.noberto.upswing.dtos.address.AddressRequest;
import br.com.noberto.upswing.dtos.address.ZipCodeRequest;
import br.com.noberto.upswing.dtos.company.RegisterCompany;
import br.com.noberto.upswing.dtos.student.RegisterStudent;
import br.com.noberto.upswing.dtos.student.StudentResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_addresses")
@AllArgsConstructor @NoArgsConstructor @Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_address")
    private UUID id;
    private Integer number;
    private String complement;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "zip_code_id")
    private ZipCode zipCode;

    @OneToMany(mappedBy = "address")
    private List<Company> companies = new ArrayList<>();

    public Address(RegisterStudent student, ZipCode zipCode) {
        this.number = student.address().number();
        this.complement = student.address().complement();
        this.zipCode = zipCode;
    }

    public Address(RegisterCompany company, ZipCode zipCode) {
        this.number = company.address().number();
        this.complement = company.address().complement();
        this.zipCode = zipCode;
    }

    public Address(AddressRequest address, ZipCode zipCode) {
        this.number = address.number();
        this.complement = address.complement();
        this.zipCode = zipCode;
    }
}
