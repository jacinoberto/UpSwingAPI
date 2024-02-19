package br.com.noberto.upswing.models;

import br.com.noberto.upswing.dtos.student.AutoApplyRequest;
import br.com.noberto.upswing.enums.Contract;
import br.com.noberto.upswing.enums.Location;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_auto_applies")
@AllArgsConstructor @NoArgsConstructor @Data
public class AutoApply {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_auto_apply")
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Contract contract;

    @Enumerated(EnumType.STRING)
    @Column(name = "offer_location")
    private Location offerLocation;

    @Column(name = "enable_auto_apply")
    private Boolean enableAutoApply;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    public AutoApply(AutoApplyRequest autoApply, Student student) {
        this.contract = Contract.fromContract(autoApply.contract());
        this.offerLocation = Location.fromLocation(autoApply.offerLocation());
        this.enableAutoApply = true;
        this.student = student;
    }
}
