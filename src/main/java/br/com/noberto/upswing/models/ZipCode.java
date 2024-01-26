package br.com.noberto.upswing.models;

import br.com.noberto.upswing.dtos.address.ZipCodeRequest;
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
}
