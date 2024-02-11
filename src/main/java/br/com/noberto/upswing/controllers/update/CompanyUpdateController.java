package br.com.noberto.upswing.controllers.update;

import br.com.noberto.upswing.dtos.company.RegisterCompany;
import br.com.noberto.upswing.dtos.company.RegisterJobOffer;
import br.com.noberto.upswing.enums.Status;
import br.com.noberto.upswing.models.Company;
import br.com.noberto.upswing.models.JobOffer;
import br.com.noberto.upswing.services.alter.ApprovalService;
import br.com.noberto.upswing.services.mail.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/company/alter")
public class CompanyUpdateController {
    private final ApprovalService approvalService;
    private final EmailService emailService;

    public CompanyUpdateController(ApprovalService approvalService, EmailService emailService) {
        this.approvalService = approvalService;
        this.emailService = emailService;
    }

    @PatchMapping("/job-approved/{jobOfferId}")
    public ResponseEntity<RegisterJobOffer> approvedJobOffer(@PathVariable UUID jobOfferId){
        JobOffer jobOffer = approvalService.approvedJobOffer(jobOfferId);
        emailService.emailForJobApplication(jobOffer);
        emailService.emailForApprovedVacancy(jobOffer);
        return ResponseEntity.ok(new RegisterJobOffer(jobOffer));
    }

    @PatchMapping("/job-not-approved/{jobOfferId}")
    public ResponseEntity<RegisterJobOffer> notApprovedJobOffer(@PathVariable UUID jobOfferId){
        JobOffer jobOffer = approvalService.notApprovedJobOffer(jobOfferId);
        emailService.emailForNotApprovedVacancy(jobOffer);
        return ResponseEntity.ok(new RegisterJobOffer(jobOffer));
    }

    @PatchMapping("/company-approved/{companyId}")
    public ResponseEntity<RegisterCompany> approvedCompany(@PathVariable UUID companyId){
        Company company = approvalService.approvedProfile(companyId);
        emailService.emailForApprovedProfile(company);
        return ResponseEntity.ok(new RegisterCompany(company));
    }

    @PatchMapping("/company-not-approved/{companyId}")
    public ResponseEntity<RegisterCompany> notApprovedCompany(@PathVariable UUID companyId){
        Company company = approvalService.notApprovedProfile(companyId);
        emailService.emailForNotApprovedProfile(company);
        return ResponseEntity.ok(new RegisterCompany(company));
    }
}
