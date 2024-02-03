package br.com.noberto.upswing.controllers.delete;

import br.com.noberto.upswing.services.delete.DisableService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/disable")
public class DisableController {
    private final DisableService service;

    DisableController(DisableService service){
        this.service = service;
    }

    @PatchMapping("/admin/{adminId}")
    @Transactional
    public ResponseEntity<Void> disableAdmin(@PathVariable UUID adminId){
        service.disableAdmin(adminId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/student/{studentId}")
    @Transactional
    public ResponseEntity<Void> disableStudent(@PathVariable UUID studentId){
        service.disableStudent(studentId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/company/{companyId}")
    @Transactional
    public ResponseEntity<Void> disableCompany(@PathVariable UUID companyId){
        service.disableCompany(companyId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/job-offer/{jobOfferId}")
    @Transactional
    public ResponseEntity<Void> disableJobOffer(@PathVariable UUID jobOfferId){
        service.disableJobOffer(jobOfferId);
        return ResponseEntity.ok().build();
    }
}
