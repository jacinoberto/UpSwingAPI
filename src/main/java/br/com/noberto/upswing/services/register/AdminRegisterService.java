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
import br.com.noberto.upswing.util.codes.IRandomCodeStrategy;
import br.com.noberto.upswing.util.codes.RandomCodeForClass;
import br.com.noberto.upswing.util.codes.RandomCodeForRegistration;
import br.com.noberto.upswing.util.verifications.AbstractCheckObject;
import br.com.noberto.upswing.util.verifications.AdminCheck;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AdminRegisterService {
    private final AdminRepository repository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final SubjectRepository subjectRepository;
    private final ClassRepository classRepository;
    private final RegistrationRepository registrationRepository;
    private final AbstractCheckObject adminCheck;
    private final IRandomCodeStrategy codeForClass;
    private final IRandomCodeStrategy codeForRegistration;
    private final GeneratePassword password;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    AdminRegisterService(AdminRepository repository, StudentRepository studentRepository, CourseRepository courseRepository,
                         SubjectRepository subjectRepository, ClassRepository classRepository, RegistrationRepository
                         registrationRepository, CompanyRepository companyRepository, BusinessAreaRepository businessAreaRepository,
                         ZipCodeRepository zipCodeRepository, GeneratePassword password, EntityManager entityManager){
        this.repository = repository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.subjectRepository = subjectRepository;
        this.classRepository = classRepository;
        this.registrationRepository = registrationRepository;
        this.codeForClass = new RandomCodeForClass(classRepository);
        this.codeForRegistration = new RandomCodeForRegistration(registrationRepository);
        this.adminCheck = new AdminCheck(zipCodeRepository, businessAreaRepository, studentRepository, classRepository, companyRepository, courseRepository, entityManager);
        this.password = password;
    }

    @Transactional
    public Student registerStudent(RegisterStudent registerStudent){
        Address address = adminCheck.checkZipCode(registerStudent.address());
        Student student = new Student(registerStudent);
        student.setAddress(address);
        student.getAccount().setPassword(password.generatePassword(registerStudent));
        entityManager.flush();
        return studentRepository.save(student);
    }

    public Admin registerAdmin(RegisterAdmin registerAdmin){
        Admin admin = new Admin(registerAdmin);
        return repository.save(admin);
    }

    public Course registerCourse(CourseRequest courseRequest){
        BusinessArea area = adminCheck.checkBusinessArea(courseRequest.businessAreaId());
        Course course = new Course(courseRequest);
        course.setBusinessArea(area);

        return courseRepository.save(course);
    }

    public Subject registerSubject(SubjectRequest subjectRequest){
        Course course = adminCheck.checkCourse(subjectRequest.courseId());
        return subjectRepository.save(new Subject(subjectRequest, course));
    }

    public Class registerClass(ClassRequest classRequest){
        Course course = adminCheck.checkCourse(classRequest.courseId());
        Integer code = codeForClass.randomCode();
        return classRepository.save(new Class(classRequest, code, course));
    }


    public Registration registrationStudent(String email, UUID classId){
        Student student = adminCheck.checkStudent(email);
        Class aClass = adminCheck.checkClass(classId);
        return registrationRepository.save(new Registration(codeForRegistration.randomCode(), student, aClass));
    }
}
