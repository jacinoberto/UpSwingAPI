package br.com.noberto.upswing.controllers.register;

import br.com.noberto.upswing.dtos.company.RegisterCompany;
import br.com.noberto.upswing.models.Company;
import br.com.noberto.upswing.services.register.CompanyRegisterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/register")
public class CompanyRegisterController {
    private final CompanyRegisterService service;

    CompanyRegisterController(CompanyRegisterService service){
        this.service = service;
    }

    @PostMapping("/company")
    @Transactional
    public ResponseEntity<RegisterCompany> registerCompany(@RequestBody @Valid RegisterCompany registerCompany, UriComponentsBuilder uriBuilder){
        Company company = service.registerCompany(registerCompany);
        URI uri = uriBuilder.path("/api/register/company/{id}").buildAndExpand(company.getId()).toUri();
        return ResponseEntity.created(uri).body(new RegisterCompany(company));
    }
}
