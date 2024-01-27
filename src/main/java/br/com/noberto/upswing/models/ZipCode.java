package br.com.noberto.upswing.models;

import br.com.noberto.upswing.dtos.address.ZipCodeRequest;
import br.com.noberto.upswing.dtos.student.RegisterStudent;
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

    public ZipCode(ZipCodeRequest zipCode) {
        this.zipCode = zipCode.zipCode();
        this.street = zipCode.street();
        this.area = zipCode.area();
        this.city = zipCode.city();
        this.state = zipCode.state();
    }

    public ZipCode(RegisterStudent student) {
        this.zipCode = student.zipCode();
        this.street = student.street();
        this.area = student.area();
        this.city = student.city();
        this.state = student.state();
    }
}
