package br.com.noberto.upswing.controllers.register;

import br.com.noberto.upswing.dtos.student.AutoApplyRequest;
import br.com.noberto.upswing.models.AutoApply;
import br.com.noberto.upswing.services.register.StudentRegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/register/student")
public class StudentRegisterController {

    private final StudentRegisterService service;

    public StudentRegisterController(StudentRegisterService service) {
        this.service = service;
    }

    @PostMapping("/auto-apply")
    @Transactional
    public ResponseEntity<AutoApplyRequest> insertAutoApply(@RequestBody AutoApplyRequest applyRequest, UriComponentsBuilder uriBuilder){
        AutoApply autoApply = service.insertAutoApply(applyRequest);
        URI uri = uriBuilder.path("api/register/student/auto-apply/{id}").buildAndExpand(autoApply.getId()).toUri();
        return ResponseEntity.created(uri).body(new AutoApplyRequest(autoApply));
    }
}
