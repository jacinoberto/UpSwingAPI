package br.com.noberto.upswing.controllers.update;

import br.com.noberto.upswing.dtos.admin.AdminResponse;
import br.com.noberto.upswing.dtos.admin.AdminUpdate;
import br.com.noberto.upswing.models.Admin;
import br.com.noberto.upswing.services.update.AdminUpdateService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/alter")
public class AdminUpdateController {
    private final AdminUpdateService service;

    public AdminUpdateController(AdminUpdateService service) {
        this.service = service;
    }

    @PatchMapping("/admin/{adminId}")
    @Transactional
    public ResponseEntity<AdminResponse> adminUpdate(@PathVariable UUID adminId, @RequestBody AdminUpdate adminUpdate){
        Admin admin = service.updateAdmin(adminId, adminUpdate);
        return ResponseEntity.ok(new AdminResponse(admin));
    }
}
