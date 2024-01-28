package br.com.noberto.upswing.controllers.list;

import br.com.noberto.upswing.dtos.company.CompanyResponse;
import br.com.noberto.upswing.repositories.CompanyRepository;
import br.com.noberto.upswing.services.list.CompanyListService;
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
@RequestMapping("/api/list/company")
public class CompanyListController {
    private final CompanyListService service;
    private final CompanyRepository repository;

    CompanyListController(CompanyListService service, CompanyRepository repository){
        this.service = service;
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> adminById(@PathVariable UUID id){
        return ResponseEntity.ok(new CompanyResponse(service.getCompany(id)));
    }

    @GetMapping()
    public ResponseEntity<Page<CompanyResponse>> companyAll(@PageableDefault(size = 6) Pageable pagination){
        var page = repository.findAllActiveProfileTrue(pagination)
                .map(CompanyResponse::new);
        return ResponseEntity.ok(page);
    }
}
