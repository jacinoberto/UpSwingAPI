package br.com.noberto.upswing.controllers.list;

import br.com.noberto.upswing.dtos.admin.AdminResponse;
import br.com.noberto.upswing.models.Admin;
import br.com.noberto.upswing.services.list.AdminListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/list")
public class AdminListController {
    private final AdminListService service;

    @Autowired
    AdminListController(AdminListService service){
        this.service = service;
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<AdminResponse> adminById(@PathVariable UUID id){
        return ResponseEntity.ok(new AdminResponse(service.getAdmin(id)));
    }

    @GetMapping("/admin")
    public ResponseEntity<List<AdminResponse>> adminById(){
        return ResponseEntity.ok(service.findAllAdmins());
    }
}
