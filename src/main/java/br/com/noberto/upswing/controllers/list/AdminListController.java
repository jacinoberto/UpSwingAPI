package br.com.noberto.upswing.controllers.list;

import br.com.noberto.upswing.dtos.academic.ClassResponseAdmin;
import br.com.noberto.upswing.dtos.academic.CourseResponseAdmin;
import br.com.noberto.upswing.dtos.admin.AdminResponse;
import br.com.noberto.upswing.dtos.company.CompanyResponse;
import br.com.noberto.upswing.dtos.company.JobOfferResponse;
import br.com.noberto.upswing.dtos.student.StudentResponseAdmin;
import br.com.noberto.upswing.repositories.*;
import br.com.noberto.upswing.services.list.AdminListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/list/admin")
public class AdminListController {
    private final AdminListService service;
    private final AdminRepository repository;
    private final CourseRepository courseRepository;
    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final JobOfferRepository jobOfferRepository;

    @Autowired
    AdminListController(AdminListService service, AdminRepository repository, CourseRepository courseRepository,
                        ClassRepository classRepository, StudentRepository studentRepository, CompanyRepository
                        companyRepository, JobOfferRepository jobOfferRepository){
        this.service = service;
        this.repository = repository;
        this.courseRepository = courseRepository;
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
        this.companyRepository = companyRepository;
        this.jobOfferRepository = jobOfferRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminResponse> adminById(@PathVariable UUID id){
        return ResponseEntity.ok(new AdminResponse(service.getAdmin(id)));
    }

    @GetMapping()
    public ResponseEntity<Page<AdminResponse>> adminAll(@PageableDefault(size = 6) Pageable pagination){
        var page = repository.findAllActiveProfileTrue(pagination)
                .map(AdminResponse::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/courses")
    public ResponseEntity<Page<CourseResponseAdmin>> courseAll(@PageableDefault(size = 6, sort = {"courseName"}) Pageable pagination){
        var page = courseRepository.findAllCourse(pagination)
                .map(CourseResponseAdmin::new);
        return ResponseEntity.ok(page);
    }
    @GetMapping("/classes")
    public ResponseEntity<Page<ClassResponseAdmin>> classesAll(@PageableDefault(size = 6, sort = {"code"}) Pageable pagination){
        var page = classRepository.findAllClass(pagination)
                .map(ClassResponseAdmin::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/students")
    public ResponseEntity<Page<StudentResponseAdmin>> studentAll(@PageableDefault(size = 6, sort = {"account.name"}) Pageable pagination){
        var page = studentRepository.findAllStudent(pagination)
                .map(StudentResponseAdmin::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/company-pending")
    public ResponseEntity<Page<CompanyResponse>> companyAllPending(@PageableDefault(size = 6, sort = {"account.name"}) Pageable pagination){
        var page = companyRepository.findAllCompanyPending(pagination)
                .map(CompanyResponse::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/job-offer-pending")
    public ResponseEntity<Page<JobOfferResponse>> jobOfferAllPending(@PageableDefault(size = 6, sort = {"company.account.name"}) Pageable pagination){
        var page = jobOfferRepository.findAllJobPending(pagination)
                .map(JobOfferResponse::new);
        return ResponseEntity.ok(page);
    }
}
