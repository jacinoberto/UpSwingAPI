package br.com.noberto.upswing.controllers.update;

import br.com.noberto.upswing.dtos.company.CompanyResponse;
import br.com.noberto.upswing.dtos.company.RegisterCompany;
import br.com.noberto.upswing.dtos.company.RegisterJobOffer;
import br.com.noberto.upswing.dtos.student.SocialNetworksUpdate;
import br.com.noberto.upswing.dtos.student.StudentResponse;
import br.com.noberto.upswing.models.Company;
import br.com.noberto.upswing.models.JobOffer;
import br.com.noberto.upswing.services.update.ApprovalService;
import br.com.noberto.upswing.services.mail.EmailService;
import br.com.noberto.upswing.services.update.CompanyUpdateService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/company/alter")
public class CompanyUpdateController {
    private final CompanyUpdateService service;


    public CompanyUpdateController(CompanyUpdateService service) {
        this.service = service;
    }

    @PatchMapping("/social-networks/{companyId}")
    @Transactional
    public ResponseEntity<CompanyResponse> studentUpdateSocialNetworks(@PathVariable String companyId, @RequestBody SocialNetworksUpdate socialNetworksUpdate){
        Company company = service.companyUpdateSocialNetworks(companyId, socialNetworksUpdate);
        return ResponseEntity.ok(new CompanyResponse(company));
    }


}
