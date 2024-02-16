package br.com.noberto.upswing.dtos.student;

import br.com.noberto.upswing.dtos.academic.CourseSelect;
import br.com.noberto.upswing.models.Class;
import br.com.noberto.upswing.models.Registration;
import br.com.noberto.upswing.models.SocialNetworks;
import br.com.noberto.upswing.models.Student;

import java.util.List;
import java.util.UUID;

public record StudentResponseCompany(
        String id,
        String name,
        String occupation,
        String status,
        SocialNetworks socialNetworks,
        List<CourseSelect> courses
) {
    public StudentResponseCompany (Student student) {
        this(
                student.getId(),
                student.getAccount().getName(),
                student.getOccupation(),
                (student.getAccount().getActiveProfile()) ? "Ativo" : "Inativo",
                student.getSocialNetworks(),
                student.getRegistrations().stream()
                        .map(Registration::getAClass)
                        .map(Class::getCourse)
                        .map(CourseSelect::new)
                        .toList()
        );
    }
}
