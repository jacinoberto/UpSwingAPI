package br.com.noberto.upswing.controllers.register;

import br.com.noberto.upswing.dtos.student.RegisterStudent;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.services.register.AdminRegisterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/register")
public class AdminRegisterController {
    private final AdminRegisterService service;

    AdminRegisterController(AdminRegisterService service){
        this.service = service;
    }

    @PostMapping("/student")
    @Transactional
    public ResponseEntity<RegisterStudent> registerStudent(@RequestBody @Valid RegisterStudent data, UriComponentsBuilder uriBuilder){
        Student student = service.registerStudent(data);
        URI uri =uriBuilder.path("/api/register/student/{id}").buildAndExpand(student.getId()).toUri();
        return ResponseEntity.created(uri).body(new RegisterStudent(student));
    }
}
