package br.com.noberto.upswing.controllers.list;

import br.com.noberto.upswing.dtos.admin.AdminResponse;
import br.com.noberto.upswing.repositories.AdminRepository;
import br.com.noberto.upswing.services.list.AdminListService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AdminListController {
    private final AdminListService service;
    private final AdminRepository repository;

    @Autowired
    AdminListController(AdminListService service, AdminRepository repository){
        this.service = service;
        this.repository = repository;
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<AdminResponse> adminById(@PathVariable UUID id){
        return ResponseEntity.ok(new AdminResponse(service.getAdmin(id)));
    }

    @GetMapping("/admin")
    public ResponseEntity<Page<AdminResponse>> adminById(@PageableDefault(size = 6) Pageable pagination){
        var page = repository.findAll(pagination)
                .map(AdminResponse::new);
        return ResponseEntity.ok(page);
    }
}
