package br.com.noberto.upswing.controllers.register;

import br.com.noberto.upswing.dtos.academic.ClassRequest;
import br.com.noberto.upswing.dtos.academic.CourseRequest;
import br.com.noberto.upswing.dtos.academic.SubjectRequest;
import br.com.noberto.upswing.dtos.admin.RegisterAdmin;
import br.com.noberto.upswing.dtos.student.RegisterStudent;
import br.com.noberto.upswing.models.Admin;
import br.com.noberto.upswing.models.Class;
import br.com.noberto.upswing.models.Course;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.models.Subject;
import br.com.noberto.upswing.services.register.AdminRegisterService;
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
public class AdminRegisterController {
    private final AdminRegisterService service;

    AdminRegisterController(AdminRegisterService service){
        this.service = service;
    }

    @PostMapping("/student")
    @Transactional
    public ResponseEntity<RegisterStudent> registerStudent(@RequestBody @Valid RegisterStudent data, UriComponentsBuilder uriBuilder){
        Student student = service.registerStudent(data);
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
}
