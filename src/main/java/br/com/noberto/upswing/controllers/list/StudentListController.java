package br.com.noberto.upswing.controllers.list;

import br.com.noberto.upswing.dtos.admin.AdminResponse;
import br.com.noberto.upswing.dtos.student.StudentResponse;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.repositories.StudentRepository;
import br.com.noberto.upswing.services.list.StudentListService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/list")
public class StudentListController {
    private final StudentRepository repository;
    private final StudentListService service;

    StudentListController(StudentRepository repository, StudentListService service){
        this.repository = repository;
        this.service = service;
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<StudentResponse> studentById(@PathVariable UUID id){
        return ResponseEntity.ok(new StudentResponse(service.getStudent(id)));
    }

    @GetMapping("/student")
    public ResponseEntity<Page<StudentResponse>> studentAll(@PageableDefault(size = 6) Pageable pagination){
        var page = repository.findAllActiveProfileTrue(pagination)
                .map(StudentResponse::new);
        return ResponseEntity.ok(page);
    }
}
