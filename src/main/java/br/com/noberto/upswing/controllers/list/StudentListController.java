package br.com.noberto.upswing.controllers.list;

import br.com.noberto.upswing.dtos.academic.CourseByBusinessArea;
import br.com.noberto.upswing.dtos.academic.CourseResponse;
import br.com.noberto.upswing.dtos.academic.CourseSelect;
import br.com.noberto.upswing.dtos.company.JobOfferResponse;
import br.com.noberto.upswing.dtos.student.StudentResponse;
import br.com.noberto.upswing.models.Course;
import br.com.noberto.upswing.models.JobOffer;
import br.com.noberto.upswing.repositories.AutoApplyRepository;
import br.com.noberto.upswing.repositories.CourseRepository;
import br.com.noberto.upswing.repositories.JobOfferRepository;
import br.com.noberto.upswing.repositories.StudentRepository;
import br.com.noberto.upswing.services.list.StudentListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/list/student")
public class StudentListController {
    private final StudentRepository repository;
    private final StudentListService service;
    private final CourseRepository courseRepository;
    private final JobOfferRepository jobOfferRepository;
    private final StudentRepository studentRepository;

    @Autowired
    StudentListController(StudentRepository repository, StudentListService service, CourseRepository courseRepository,
                          JobOfferRepository jobOfferRepository, StudentRepository studentRepository){
        this.repository = repository;
        this.service = service;
        this.courseRepository = courseRepository;
        this.jobOfferRepository = jobOfferRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> studentById(@PathVariable String id){
        return ResponseEntity.ok(new StudentResponse(service.getStudent(id)));
    }

    @GetMapping()
    public ResponseEntity<Page<StudentResponse>> studentAll(@PageableDefault(size = 8) Pageable pagination){
        var page = repository.findAllActiveProfileTrue(pagination)
                .map(StudentResponse::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/course/{businessAreaId}")
    public ResponseEntity<Page<CourseByBusinessArea>> allCoursesByBusinessArea(@PathVariable UUID businessAreaId, @PageableDefault(size = 8) Pageable pagination){
        var page = courseRepository.findAllBusinessAreaById(businessAreaId, pagination)
                .map(CourseByBusinessArea::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/my-course/{studentId}")
    public ResponseEntity<Page<CourseResponse>> myCourses(@PathVariable String studentId, @PageableDefault(size = 8) Pageable pagination){
        var page = courseRepository.findAllStudentTrue(studentId, pagination)
                .map(CourseResponse::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/my-job-offers/{studentId}")
    public ResponseEntity<Page<JobOfferResponse>> myJobOffers(@PathVariable String studentId, @PageableDefault(size = 8) Pageable pagination){
        var page = jobOfferRepository.findByStudentTrue(studentId, LocalDate.now(),pagination)
                .map(JobOfferResponse::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/auto-apply/{studentId}")
    public Boolean autoApplyResponse(@PathVariable String studentId){
        return studentRepository.existsByStudent(studentId).isPresent();
    }
}
