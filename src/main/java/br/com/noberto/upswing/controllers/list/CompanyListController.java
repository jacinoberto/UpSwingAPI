package br.com.noberto.upswing.controllers.list;

import br.com.noberto.upswing.dtos.academic.CourseSelect;
import br.com.noberto.upswing.dtos.company.CompanyResponse;
import br.com.noberto.upswing.dtos.company.JobOfferResponseCompany;
import br.com.noberto.upswing.dtos.company.VacancyOfferResponse;
import br.com.noberto.upswing.dtos.student.StudentResponseCompany;
import br.com.noberto.upswing.models.Course;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.repositories.*;
import br.com.noberto.upswing.services.list.CompanyListService;
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
    private final JobOfferRepository jobOfferRepository;
    private final VacancyOfferRepository vacancyOfferRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    @Autowired
    CompanyListController(CompanyListService service, JobOfferRepository jobOfferRepository, VacancyOfferRepository vacancyOfferRepository, StudentRepository studentRepository, CourseRepository courseRepository){
        this.service = service;
        this.jobOfferRepository = jobOfferRepository;
        this.vacancyOfferRepository = vacancyOfferRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> adminById(@PathVariable String id){
        return ResponseEntity.ok(new CompanyResponse(service.getCompany(id)));
    }

    @GetMapping("/my-vacancies/{companyId}")
    public ResponseEntity<Page<JobOfferResponseCompany>> vacancyAll(@PathVariable String companyId, @PageableDefault(size = 8) Pageable pagination){
        var page = jobOfferRepository.findAllMyVacancies(companyId, pagination)
                .map(JobOfferResponseCompany::new);
        return ResponseEntity.ok(page);
    }

//    @GetMapping("/my-candidates/{companyId}")
//    public ResponseEntity<Page<VacancyOfferResponse>> candidatesAll(@PathVariable String companyId, @PageableDefault(size = 8) Pageable pagination){
//        var page = vacancyOfferRepository.findAllCandidates(companyId, pagination)
//                .map(VacancyOfferResponse::new);
//        return ResponseEntity.ok(page);
//    }

    @GetMapping("/my-candidates/{jobOfferId}")
    public ResponseEntity<Page<StudentResponseCompany>> candidates(@PathVariable UUID jobOfferId, @PageableDefault(size = 8) Pageable pagination){
        var page = studentRepository.findStudentsByVacancyOffer(jobOfferId, pagination)
                .map(StudentResponseCompany::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/candidate/{vacancyOfferId}")
    public ResponseEntity<StudentResponseCompany> candidatesById(@PathVariable UUID vacancyOfferId){
        Student student = studentRepository.findStudentByVacancyOffer(vacancyOfferId);
        return ResponseEntity.ok(new StudentResponseCompany(student));
    }
}
