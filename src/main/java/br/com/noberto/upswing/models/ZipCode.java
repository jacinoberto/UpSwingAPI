package br.com.noberto.upswing.models;

import br.com.noberto.upswing.dtos.address.AddressRequest;
import br.com.noberto.upswing.dtos.address.ZipCodeRequest;
import br.com.noberto.upswing.dtos.company.RegisterCompany;
import br.com.noberto.upswing.dtos.student.RegisterStudent;
import br.com.noberto.upswing.repositories.ZipCodeRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_zip_codes")
@AllArgsConstructor @NoArgsConstructor @Data
public class ZipCode {

    @Id
    @Column(name = "zip_code")
    private String zipCode;
    private String street;
    private String area;
    private String city;
    private String state;

    @OneToMany(mappedBy = "zipCode")
    private List<Address> addresses = new ArrayList<>();

    public ZipCode(RegisterStudent student) {
        this.zipCode = student.address().zipCode().zipCode();
        this.street = student.address().zipCode().street();
        this.area = student.address().zipCode().area();
        this.city = student.address().zipCode().city();
        this.state = student.address().zipCode().state();
    }

    public ZipCode(RegisterCompany company) {
        this.zipCode = company.address().zipCode().zipCode();
        this.street = company.address().zipCode().street();
        this.area = company.address().zipCode().area();
        this.city = company.address().zipCode().city();
        this.state = company.address().zipCode().state();
    }
    public ZipCode(ZipCodeRequest zipCode) {
        this.zipCode = zipCode.zipCode();
        this.street = zipCode.street();
        this.area = zipCode.area();
        this.city = zipCode.city();
        this.state = zipCode.state();
    }

}
