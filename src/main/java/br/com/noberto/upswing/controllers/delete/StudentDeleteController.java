package br.com.noberto.upswing.controllers.delete;

import br.com.noberto.upswing.services.delete.StudentDeleteService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/student/delete")
public class StudentDeleteController {
    private final StudentDeleteService service;

    StudentDeleteController(StudentDeleteService service){
        this.service = service;
    }

    @DeleteMapping("/{studentId}")
    @Transactional
    public ResponseEntity<Void> disableStudent(@PathVariable UUID studentId){
        service.disableStudent(studentId);
        return ResponseEntity.noContent().build();
    }
}
