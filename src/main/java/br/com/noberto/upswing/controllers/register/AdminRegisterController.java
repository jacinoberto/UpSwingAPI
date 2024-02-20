package br.com.noberto.upswing.controllers.register;

import br.com.noberto.upswing.dtos.academic.*;
import br.com.noberto.upswing.dtos.admin.RegisterAdmin;
import br.com.noberto.upswing.dtos.student.RegisterStudent;
import br.com.noberto.upswing.models.*;
import br.com.noberto.upswing.models.Class;
import br.com.noberto.upswing.services.mail.EmailService;
import br.com.noberto.upswing.services.register.AdminRegisterService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/register")
public class AdminRegisterController {
    private final AdminRegisterService service;
    private final EmailService emailService;

    AdminRegisterController(AdminRegisterService service, EmailService emailService)
    {
        this.service = service;
        this.emailService = emailService;
    }

    @PostMapping("/student")
    public ResponseEntity<RegisterStudent> registerStudent(@RequestBody @Valid RegisterStudent data, UriComponentsBuilder uriBuilder) throws MessagingException {
        Student student = service.registerStudent(data);
        emailService.welcomeEmail(student);
        URI uri =uriBuilder.path("/api/register/student/{id}").buildAndExpand(student.getId()).toUri();
        return ResponseEntity.created(uri).body(new RegisterStudent(student));
    }

    @PostMapping("/admin")
    @Transactional
    public ResponseEntity<RegisterAdmin> registerAdmin(@RequestBody @Valid RegisterAdmin data, UriComponentsBuilder uriBuilder){
        Admin admin = service.registerAdmin(data);
        URI uri =uriBuilder.path("/api/register/admin/{id}").buildAndExpand(admin.getId()).toUri();
        return ResponseEntity.created(uri).body(new RegisterAdmin(admin));
    }

    @PostMapping("/course")
    @Transactional
    public ResponseEntity<CourseRequest> registerCourse(@RequestBody @Valid CourseRequest courseRequest, UriComponentsBuilder uriBuilder){
        Course course = service.registerCourse(courseRequest);
        URI uri = uriBuilder.path("/api/register/course/{id}").buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(uri).body(new CourseRequest(course));
    }

    @PostMapping("/subject")
    @Transactional
    public ResponseEntity<SubjectRequest> registerCourse(@RequestBody @Valid SubjectRequest subjectRequest, UriComponentsBuilder uriBuilder){
        Subject subject = service.registerSubject(subjectRequest);
        URI uri = uriBuilder.path("/api/register/subject/{id}").buildAndExpand(subject.getId()).toUri();
        return ResponseEntity.created(uri).body(new SubjectRequest(subject));
    }

    @PostMapping("/class")
    @Transactional
    public ResponseEntity<ClassRequest> registerCourse(@RequestBody @Valid ClassRequest classRequest, UriComponentsBuilder uriBuilder){
        Class aClass = service.registerClass(classRequest);
        URI uri = uriBuilder.path("/api/register/class/{id}").buildAndExpand(aClass.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClassRequest(aClass));
    }

    @PostMapping("/registration")
    @Transactional
    public ResponseEntity<List<RegistrationReturn>> registerStudent(@RequestBody RegistrationRequest registrationRequest){
        List<Registration> registrations = service.registrationStudent(registrationRequest.emails(), registrationRequest.classId());
        List<RegistrationReturn> registrationReturns = registrations.stream().map(RegistrationReturn::new).toList();
        return ResponseEntity.ok(registrationReturns);
    }

    @PostMapping("/completed-subject")
    @Transactional
    public ResponseEntity<CompletedSubjectResponse> saveCompletedSubjects(@RequestBody CompletedSubjectRequest completedSubjectRequest, UriComponentsBuilder uriBuilder){
        CompletedSubject completedSubject = service.saveCompletedSubjects(completedSubjectRequest);
        URI uri = uriBuilder.path("api/register/completed-subject/{completedSubjectId}").buildAndExpand(completedSubject.getId()).toUri();
        return ResponseEntity.created(uri).body(new CompletedSubjectResponse(completedSubject));
    }
}
