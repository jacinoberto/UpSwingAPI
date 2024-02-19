package br.com.noberto.upswing.dtos.student;

import br.com.noberto.upswing.dtos.academic.ClassResponse;
import br.com.noberto.upswing.models.Class;
import br.com.noberto.upswing.models.Registration;
import br.com.noberto.upswing.models.Student;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public record StudentResponseAdmin (
        String name,
        List<ClassResponse> classes,
        Boolean status
){
    public StudentResponseAdmin (Student student){
        this(
                student.getAccount().getName(),
                student.getRegistrations().stream()
                        .map(Registration::getAClass)
                        .map(ClassResponse::new)
                        .toList(),
                student.getAccount().getActiveProfile()
        );
    }

}
