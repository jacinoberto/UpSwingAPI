package br.com.noberto.upswing.controllers.list;

import br.com.noberto.upswing.dtos.academic.CourseSelect;
import br.com.noberto.upswing.dtos.company.CompanyResponse;
import br.com.noberto.upswing.dtos.company.JobOfferResponseCompany;
import br.com.noberto.upswing.dtos.student.StudentResponse;
import br.com.noberto.upswing.models.Course;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.repositories.*;
import br.com.noberto.upswing.services.list.CompanyListService;
import br.com.noberto.upswing.services.mail.EmailService;
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
    private final StudentRepository studentRepository;
    private final AutoApplyRepository autoApplyRepository;
    private final VacancyAndCourseRepository vacancyAndCourseRepository;
    private final EmailService emailService;

    @Autowired
    CompanyListController(CompanyListService service, CompanyRepository repository, JobOfferRepository jobOfferRepository,
                          StudentRepository studentRepository, AutoApplyRepository autoApplyRepository, VacancyAndCourseRepository vacancyAndCourseRepository, EmailService emailService){
        this.service = service;
        this.repository = repository;
        this.jobOfferRepository = jobOfferRepository;
        this.studentRepository = studentRepository;
        this.autoApplyRepository = autoApplyRepository;
        this.vacancyAndCourseRepository = vacancyAndCourseRepository;
        this.emailService = emailService;
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

    @GetMapping("/my-vacancies/{companyId}")
    public ResponseEntity<Page<JobOfferResponseCompany>> vacancyAll(@PathVariable UUID companyId, @PageableDefault(size = 6) Pageable pagination){
        var page = jobOfferRepository.findAllMyVacancies(companyId, pagination)
                .map(JobOfferResponseCompany::new);
        return ResponseEntity.ok(page);
    }

//    @GetMapping("/teste/{companyId}")
//    public ResponseEntity<List<StudentResponse>> teste(@PathVariable UUID companyId){
//        List<Student> students = studentRepository.findByStateTrue(companyId);
//        List<StudentResponse> responses = students.stream().map(StudentResponse::new).toList();
//        System.out.println(responses);
//        return ResponseEntity.ok(responses);
//    }
//
//    @GetMapping("/teste-dois")
//    public ResponseEntity<List<StudentResponse>> testeDois(){
//        List<Student> students = studentRepository.findByAutoApplyTrue();
//        List<StudentResponse> responses = students.stream().map(StudentResponse::new).toList();
//        System.out.println(responses);
//        return ResponseEntity.ok(responses);
//    }
//
//    @GetMapping("/teste-dois/{id}")
//    public ResponseEntity<AutoApplyRequest> testeTres(@PathVariable UUID id){
//        AutoApply autoApply = autoApplyRepository.findByStudentPresentAutoApply(id);
//        return ResponseEntity.ok(new AutoApplyRequest(autoApply));
//    }
//
//    @GetMapping("/teste-quatro/{id}")
//    public ResponseEntity<List<StudentResponse>> testequatro(@PathVariable UUID id){
//        List<Student> students = service.filterStudents(id);
//        List<StudentResponse> studentResponses = students.stream().map(StudentResponse::new).toList();
//        return ResponseEntity.ok(studentResponses);
//    }
//
//    @GetMapping("/teste-cinco/{id}")
//    public ResponseEntity<List<StudentResponse>> testecinco(@PathVariable UUID id){
//        List<Student> students = studentRepository.findByBusinessArea(id);
//        List<StudentResponse> studentResponses = students.stream().map(StudentResponse::new).toList();
//        return ResponseEntity.ok(studentResponses);
//    }

    @GetMapping("/teste-cinco/{courseId}")
    public ResponseEntity<List<StudentResponse>> testecinco(@PathVariable UUID courseId){
        List<Student> students = studentRepository.findByStudentsExistsInCourse(courseId);
        List<StudentResponse> studentResponses = students.stream().map(StudentResponse::new).toList();
        return ResponseEntity.ok(studentResponses);
    }

    @GetMapping("/teste-seis/{jobOfferId}")
    public ResponseEntity<List<CourseSelect>> testeSeis(@PathVariable UUID jobOfferId){
        List<Course> courses = vacancyAndCourseRepository.findByCoursesExistsInVacancyAndCourse(jobOfferId);
        List<CourseSelect> courseSelects = courses.stream().map(CourseSelect::new).toList();
        return ResponseEntity.ok(courseSelects);
    }

    @GetMapping("/teste-sete/{jobOfferId}")
    public ResponseEntity<List<StudentResponse>> testeSete(@PathVariable UUID jobOfferId){
        List<Student> students = emailService.filterStudentsByAddressAndContract(jobOfferId);
        List<StudentResponse> studentResponses = students.stream().map(StudentResponse::new).toList();
        return ResponseEntity.ok(studentResponses);
    }
}
