package br.com.noberto.upswing.services.register;

import br.com.noberto.upswing.dtos.academic.ClassRequest;
import br.com.noberto.upswing.dtos.academic.CourseRequest;
import br.com.noberto.upswing.dtos.academic.SubjectRequest;
import br.com.noberto.upswing.dtos.admin.RegisterAdmin;
import br.com.noberto.upswing.dtos.student.RegisterStudent;
import br.com.noberto.upswing.models.*;
import br.com.noberto.upswing.models.Class;
import br.com.noberto.upswing.repositories.*;
import br.com.noberto.upswing.util.GeneratePassword;
import br.com.noberto.upswing.util.codes.RandomCodeForClass;
import br.com.noberto.upswing.util.codes.RandomCodeForRegistration;
import br.com.noberto.upswing.util.verifications.AdminCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminRegisterService {
    private final AdminRepository repository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final SubjectRepository subjectRepository;
    private final ClassRepository classRepository;
    private final RegistrationRepository registrationRepository;
    private final AdminCheck check;
    private final RandomCodeForClass codeForClass;
    private final RandomCodeForRegistration codeForRegistration;
    private final GeneratePassword password;

    @Autowired
    AdminRegisterService(AdminRepository repository, StudentRepository studentRepository, CourseRepository courseRepository,
                         SubjectRepository subjectRepository, ClassRepository classRepository, RegistrationRepository
                         registrationRepository, AdminCheck check, RandomCodeForClass codeForClass, RandomCodeForRegistration
                         codeForRegistration, GeneratePassword password){
        this.repository = repository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.subjectRepository = subjectRepository;
        this.classRepository = classRepository;
        this.registrationRepository = registrationRepository;
        this.check = check;
        this.codeForClass = codeForClass;
        this.codeForRegistration = codeForRegistration;
        this.password = password;
    }

    public Student registerStudent(RegisterStudent registerStudent){
        Address address = check.checkZipCode(registerStudent.address());
        Student student = new Student(registerStudent);
        student.setAddress(address);
        student.getAccount().setPassword(password.generatePassword(registerStudent));
        return studentRepository.save(student);
    }

    public Admin registerAdmin(RegisterAdmin registerAdmin){
        Admin admin = new Admin(registerAdmin);
        return repository.save(admin);
    }

    public Course registerCourse(CourseRequest courseRequest){
        BusinessArea area = check.checkBusinessArea(courseRequest.businessAreaId());
        Course course = new Course(courseRequest);
        course.setBusinessArea(area);

        return courseRepository.save(course);
    }

    public Subject registerSubject(SubjectRequest subjectRequest){
        Course course = check.checkCourse(subjectRequest.courseId());
        return subjectRepository.save(new Subject(subjectRequest, course));
    }

    public Class registerClass(ClassRequest classRequest){
        Course course = check.checkCourse(classRequest.courseId());
        Integer code = codeForClass.randomCode();
        return classRepository.save(new Class(classRequest, code, course));
    }

    public Registration registrationStudent(String email, UUID classId){
        Student student = check.checkStudent(email);
        Class aClass = check.checkClass(classId);
        return registrationRepository.save(new Registration(codeForRegistration.randomCode(), student, aClass));
    }
}
