package br.com.noberto.upswing.services.register;

import br.com.noberto.upswing.dtos.academic.ClassRequest;
import br.com.noberto.upswing.dtos.academic.CompletedSubjectRequest;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AdminRegisterService {
    private final AdminRepository repository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final SubjectRepository subjectRepository;
    private final ClassRepository classRepository;
    private final RegistrationRepository registrationRepository;
    private final CompletedSubjectRepository completedSubjectRepository;
    private final AbstractCheckObject adminCheck;
    private final IRandomCodeStrategy codeForClass;
    private final IRandomCodeStrategy codeForRegistration;
    private final GeneratePassword password;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    AdminRegisterService(AdminRepository repository, StudentRepository studentRepository, CourseRepository courseRepository,
                         SubjectRepository subjectRepository, ClassRepository classRepository, RegistrationRepository
                         registrationRepository, CompletedSubjectRepository completedSubjectRepository, CompanyRepository companyRepository, BusinessAreaRepository businessAreaRepository,
                         ZipCodeRepository zipCodeRepository, GeneratePassword password, EntityManager entityManager){
        this.repository = repository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.subjectRepository = subjectRepository;
        this.classRepository = classRepository;
        this.registrationRepository = registrationRepository;
        this.codeForClass = new RandomCodeForClass(classRepository);
        this.codeForRegistration = new RandomCodeForRegistration(registrationRepository);
        this.completedSubjectRepository = completedSubjectRepository;
        this.adminCheck = new AdminCheck(zipCodeRepository, businessAreaRepository, studentRepository, classRepository, companyRepository, courseRepository, entityManager);
        this.password = password;
    }

    @Transactional
    public Student registerStudent(RegisterStudent registerStudent){
        Address address = adminCheck.checkZipCode(registerStudent.address());
        Student student = new Student(registerStudent);
        student.setAddress(address);
        student.getAccount().setPassword(new BCryptPasswordEncoder().encode(password.generatePassword(registerStudent)));
        entityManager.flush();
        return studentRepository.save(student);
    }

    public Admin registerAdmin(RegisterAdmin registerAdmin){
        Admin admin = new Admin(registerAdmin);
        admin.getAccount().setPassword(new BCryptPasswordEncoder().encode(registerAdmin.account().getPassword()));
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


//    public Registration registrationStudent(String email, UUID classId){
//        Student student = adminCheck.checkStudent(email);
//        Class aClass = adminCheck.checkClass(classId);
//        return registrationRepository.save(new Registration(codeForRegistration.randomCode(), student, aClass));
//    }

    public List<Registration> registrationStudent(List<String> emails, UUID classId){
        List<Registration> registrations = new ArrayList<>();
        for (String email: emails) {
            Student student = adminCheck.checkStudent(email);
            Class aClass = adminCheck.checkClass(classId);
            registrations.add(registrationRepository.save(new Registration(codeForRegistration.randomCode(), student, aClass)));
        }
        return registrations;
    }

    public CompletedSubject saveCompletedSubjects(CompletedSubjectRequest completedSubjectRequest){
        CompletedSubject completedSubject = new CompletedSubject();
        completedSubject.setSubject(subjectRepository.getReferenceById(completedSubjectRequest.subjectId()));
        completedSubject.setAClass(classRepository.getReferenceById(completedSubjectRequest.classId()));
        completedSubject.setComplete(completedSubjectRequest.complete());
        return completedSubjectRepository.save(completedSubject);
    }
}
