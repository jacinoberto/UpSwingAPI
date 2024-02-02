package br.com.noberto.upswing.controllers.list;

import br.com.noberto.upswing.dtos.academic.CourseResponseAdmin;
import br.com.noberto.upswing.dtos.admin.AdminResponse;
import br.com.noberto.upswing.repositories.AdminRepository;
import br.com.noberto.upswing.repositories.CourseRepository;
import br.com.noberto.upswing.services.list.AdminListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/list")
public class AdminListController {
    private final AdminListService service;
    private final AdminRepository repository;
    private final CourseRepository courseRepository;

    @Autowired
    AdminListController(AdminListService service, AdminRepository repository, CourseRepository courseRepository){
        this.service = service;
        this.repository = repository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<AdminResponse> adminById(@PathVariable UUID id){
        return ResponseEntity.ok(new AdminResponse(service.getAdmin(id)));
    }

    @GetMapping("/admin")
    public ResponseEntity<Page<AdminResponse>> adminAll(@PageableDefault(size = 6) Pageable pagination){
        var page = repository.findAllActiveProfileTrue(pagination)
                .map(AdminResponse::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/admin/courses")
    public ResponseEntity<Page<CourseResponseAdmin>> courseAll(@PageableDefault(size = 6, sort = {"courseName"}) Pageable pagination){
        var page = courseRepository.findAll(pagination)
                .map(CourseResponseAdmin::new);
        return ResponseEntity.ok(page);
    }
}
