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

    public ZipCode(RegisterStudent student) {
        this.zipCode = student.address().zipCode().zipCode();
        this.street = student.address().zipCode().street();
        this.area = student.address().zipCode().area();
        this.city = student.address().zipCode().city();
        this.state = student.address().zipCode().state();
    }
}
