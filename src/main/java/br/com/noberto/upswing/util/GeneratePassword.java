package br.com.noberto.upswing.util;

import br.com.noberto.upswing.dtos.student.RegisterStudent;
import org.springframework.stereotype.Component;

@Component
public class GeneratePassword {
    public String generatePassword(RegisterStudent student){
        String name = student.account().getName().substring(0, 2);
        String socialSecurity = student.socialSecurity().substring(0, 3);
        return "#" + name + socialSecurity;
    }
}
