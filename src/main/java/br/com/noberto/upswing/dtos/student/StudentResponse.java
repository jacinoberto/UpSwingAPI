package br.com.noberto.upswing.dtos.student;

import br.com.noberto.upswing.dtos.address.AddressRequest;
import br.com.noberto.upswing.models.SocialNetworks;
import br.com.noberto.upswing.models.Student;

import java.util.UUID;

public record StudentResponse(
        String id,
        String name,
        String occupation,
        SocialNetworks socialNetworks,
        String mainPhone,
        String mail,
        AddressRequest address
) {
    public StudentResponse (Student student){
        this(
                student.getId(),
                student.getAccount().getName(),
                student.getOccupation(),
                student.getSocialNetworks(),
                student.getAccount().getMainPhone(),
                student.getAccount().getEmail(),
                new AddressRequest(student.getAddress())
        );
    }
}
