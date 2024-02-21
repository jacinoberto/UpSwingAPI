package br.com.noberto.upswing.controllers.register;

import br.com.noberto.upswing.dtos.company.RegisterCompany;
import br.com.noberto.upswing.dtos.company.RegisterJobOffer;
import br.com.noberto.upswing.enums.Status;
import br.com.noberto.upswing.models.Company;
import br.com.noberto.upswing.models.JobOffer;
import br.com.noberto.upswing.repositories.JobOfferRepository;
import br.com.noberto.upswing.services.mail.EmailService;
import br.com.noberto.upswing.services.register.CompanyRegisterService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/register")
public class CompanyRegisterController {
    private final CompanyRegisterService service;
    private final EmailService emailService;

    CompanyRegisterController(CompanyRegisterService service, EmailService emailService){
        this.service = service;
        this.emailService = emailService;
    }

    @PostMapping("/company")
    public ResponseEntity<RegisterCompany> registerCompany(@RequestBody @Valid RegisterCompany registerCompany, UriComponentsBuilder uriBuilder) throws MessagingException {
        Company company = service.registerCompany(registerCompany);
        URI uri = uriBuilder.path("/api/register/company/{id}").buildAndExpand(company.getId()).toUri();
        emailService.emailForPendingProfile(company);
        return ResponseEntity.created(uri).body(new RegisterCompany(company));
    }

    @PostMapping("/job-offer")
    public ResponseEntity<RegisterJobOffer> registerJobOffer(@RequestBody @Valid RegisterJobOffer registerJobOffer, UriComponentsBuilder uriBuilder) throws MessagingException {
        JobOffer jobOffer = service.registerJobOffer(registerJobOffer);
        URI uri = uriBuilder.path("/api/register/job-offer/{id}").buildAndExpand(jobOffer.getId()).toUri();
        emailService.emailForPendingVacancy(jobOffer);
        return ResponseEntity.created(uri).body(new RegisterJobOffer(jobOffer));
    }
}
