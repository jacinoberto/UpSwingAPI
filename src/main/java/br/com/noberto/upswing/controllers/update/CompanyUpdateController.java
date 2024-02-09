package br.com.noberto.upswing.controllers.update;

import br.com.noberto.upswing.dtos.company.RegisterCompany;
import br.com.noberto.upswing.dtos.company.RegisterJobOffer;
import br.com.noberto.upswing.enums.Status;
import br.com.noberto.upswing.models.Company;
import br.com.noberto.upswing.models.JobOffer;
import br.com.noberto.upswing.repositories.CompanyRepository;
import br.com.noberto.upswing.repositories.JobOfferRepository;
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
    private final CompanyRepository repository;
    private final JobOfferRepository jobOfferRepository;
    private final EmailService emailService;

    public CompanyUpdateController(CompanyRepository repository, JobOfferRepository jobOfferRepository, EmailService emailService) {
        this.repository = repository;
        this.jobOfferRepository = jobOfferRepository;
        this.emailService = emailService;
    }

    @PatchMapping("/job-approved/{jobOfferId}")
    @Transactional
    public ResponseEntity<RegisterJobOffer> approvedJobOffer(@PathVariable UUID jobOfferId){
        JobOffer jobOffer = jobOfferRepository.getReferenceById(jobOfferId);
        jobOffer.setStatus(Status.APPROVED);
        emailService.emailForJobApplication(jobOffer);
        emailService.emailForApprovedVacancy(jobOffer);
        return ResponseEntity.ok(new RegisterJobOffer(jobOffer));
    }

    @PatchMapping("/job-not-approved/{jobOfferId}")
    @Transactional
    public ResponseEntity<RegisterJobOffer> notApprovedJobOffer(@PathVariable UUID jobOfferId){
        JobOffer jobOffer = jobOfferRepository.getReferenceById(jobOfferId);
        jobOffer.setStatus(Status.NOT_APPROVED);
        emailService.emailForNotApprovedVacancy(jobOffer);
        return ResponseEntity.ok(new RegisterJobOffer(jobOffer));
    }

    @PatchMapping("/company-approved/{companyId}")
    @Transactional
    public ResponseEntity<RegisterCompany> approvedCompany(@PathVariable UUID companyId){
        Company company = repository.getReferenceById(companyId);
        company.setStatus(Status.APPROVED);
        emailService.emailForApprovedProfile(company);
        return ResponseEntity.ok(new RegisterCompany(company));
    }

    @PatchMapping("/company-not-approved/{companyId}")
    @Transactional
    public ResponseEntity<RegisterCompany> notApprovedCompany(@PathVariable UUID companyId){
        Company company = repository.getReferenceById(companyId);
        company.setStatus(Status.NOT_APPROVED);
        emailService.emailForNotApprovedProfile(company);
        return ResponseEntity.ok(new RegisterCompany(company));
    }
}
