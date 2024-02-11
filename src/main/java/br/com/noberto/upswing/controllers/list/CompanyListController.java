package br.com.noberto.upswing.controllers.list;

import br.com.noberto.upswing.dtos.company.CompanyResponse;
import br.com.noberto.upswing.dtos.company.JobOfferResponseCompany;
import br.com.noberto.upswing.dtos.student.StudentResponse;
import br.com.noberto.upswing.models.JobOffer;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.repositories.*;
import br.com.noberto.upswing.services.list.CompanyListService;
import br.com.noberto.upswing.util.filters.FilterStudentByAddressStrategy;
<<<<<<< HEAD
import br.com.noberto.upswing.util.filters.FilterStudentsByContractTypeStrategy;
=======
import br.com.noberto.upswing.util.filters.FilterStudentsWithCompatibilityStrategy;
>>>>>>> 05b5d2520ad07188ad0e2dddf953d8048b6363d4
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/list/company")
public class CompanyListController {
    private final CompanyListService service;
    private final CompanyRepository repository;
    private final JobOfferRepository jobOfferRepository;
<<<<<<< HEAD
    private final StudentRepository studentRepository;
    private final FilterStudentsByContractTypeStrategy filter;

    @Autowired
    CompanyListController(CompanyListService service, CompanyRepository repository, JobOfferRepository jobOfferRepository, StudentRepository studentRepository, FilterStudentsByContractTypeStrategy filter){
        this.service = service;
        this.repository = repository;
        this.jobOfferRepository = jobOfferRepository;
        this.studentRepository = studentRepository;
=======
    private final FilterStudentsWithCompatibilityStrategy filter;

    @Autowired
    CompanyListController(CompanyListService service, CompanyRepository repository, JobOfferRepository jobOfferRepository, FilterStudentsWithCompatibilityStrategy filter){
        this.service = service;
        this.repository = repository;
        this.jobOfferRepository = jobOfferRepository;
>>>>>>> 05b5d2520ad07188ad0e2dddf953d8048b6363d4
        this.filter = filter;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> adminById(@PathVariable UUID id){
        return ResponseEntity.ok(new CompanyResponse(service.getCompany(id)));
    }

    @GetMapping("/my-vacancies/{companyId}")
    public ResponseEntity<Page<JobOfferResponseCompany>> vacancyAll(@PathVariable UUID companyId, @PageableDefault(size = 6) Pageable pagination){
        var page = jobOfferRepository.findAllMyVacancies(companyId, pagination)
                .map(JobOfferResponseCompany::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/teste/{id}")
    public ResponseEntity<List<StudentResponse>> vacancyAll(@PathVariable UUID id){
        JobOffer jobOffer = jobOfferRepository.getReferenceById(id);
        List<Student> students = filter.filterStudents(jobOffer);
<<<<<<< HEAD
//        List<Student> students = studentRepository.findByStateTrue(jobOffer.getCompany().getId());
=======
>>>>>>> 05b5d2520ad07188ad0e2dddf953d8048b6363d4
        List<StudentResponse> studentResponses = students.stream().map(StudentResponse::new).toList();
        return ResponseEntity.ok(studentResponses);
    }
}
