package br.com.noberto.upswing.services.register;

import br.com.noberto.upswing.dtos.academic.ClassRequest;
import br.com.noberto.upswing.dtos.academic.CourseRequest;
import br.com.noberto.upswing.dtos.academic.SubjectRequest;
import br.com.noberto.upswing.dtos.admin.RegisterAdmin;
import br.com.noberto.upswing.dtos.student.RegisterStudent;
import br.com.noberto.upswing.models.*;
import br.com.noberto.upswing.models.Class;
import br.com.noberto.upswing.repositories.*;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class AdminRegisterService {
    private final AdminRepository repository;
    private final StudentRepository studentRepository;
    private final ZipCodeRepository zipCodeRepository;
    private final BusinessAreaRepository businessAreaRepository;
    private final CourseRepository courseRepository;
    private final SubjectRepository subjectRepository;
    private final ClassRepository classRepository;
    private final RegistrationRepository registrationRepository;

    @Autowired
    AdminRegisterService(AdminRepository repository, StudentRepository studentRepository, ZipCodeRepository
            zipCodeRepository, CourseRepository courseRepository, SubjectRepository subjectRepository, BusinessAreaRepository
            businessAreaRepository, ClassRepository classRepository, RegistrationRepository registrationRepository){
        this.repository = repository;
        this.studentRepository = studentRepository;
        this.zipCodeRepository = zipCodeRepository;
        this.businessAreaRepository = businessAreaRepository;
        this.courseRepository = courseRepository;
        this.subjectRepository = subjectRepository;
        this.classRepository = classRepository;
        this.registrationRepository = registrationRepository;
    }

    public Student registerStudent(RegisterStudent registerStudent){
        Address address = checkAddress(registerStudent);
        Student student = new Student(registerStudent);
        student.setAddress(address);
        student.getAccount().setPassword(generatePassword(registerStudent));
        return studentRepository.save(student);
    }

    public Admin registerAdmin(RegisterAdmin registerAdmin){
        Admin admin = new Admin(registerAdmin);
        return repository.save(admin);
    }

    public Course registerCourse(CourseRequest courseRequest){
        BusinessArea area = checkBusinessArea(courseRequest.businessAreaId());
        Course course = new Course(courseRequest);
        course.setBusinessArea(area);

        return courseRepository.save(course);
    }

    public Subject registerSubject(SubjectRequest subjectRequest){
        Course course = checkCourse(subjectRequest.courseId());
        return subjectRepository.save(new Subject(subjectRequest, course));
    }

    public Class registerClass(ClassRequest classRequest){
        Course course = checkCourse(classRequest.courseId());
        Integer code = generateRandomCode();
        return classRepository.save(new Class(classRequest, code, course));
    }

    public Registration registrationStudent(String email, UUID classId){
        Student student = checkStudent(email);
        Class aClass = checkClass(classId);
        return registrationRepository.save(new Registration(generateRandomCode(), student, aClass));
    }

    /*
      METHODS
    */
    private Address checkAddress(RegisterStudent data){
        ZipCode zipCode = null;

        if (zipCodeRepository.existsById(data.address().zipCode().zipCode())){
            zipCode = zipCodeRepository.getReferenceById(data.address().zipCode().zipCode());
        } else {
            zipCode = zipCodeRepository.save(new ZipCode(data));
        }

        return new Address(data, zipCode);
    }

    private Course checkCourse(UUID id){
        if (courseRepository.existsById(id)){
            return courseRepository.getReferenceById(id);
        }
        throw new ValidationException("ID informado para Disciplina é invalido");
    }

    private BusinessArea checkBusinessArea(UUID id){
        if (businessAreaRepository.existsById(id)){
            return businessAreaRepository.getReferenceById(id);
        }

        throw new ValidationException("ID informado para Área de Atuação é invalido!");
    }

    private Integer generateRandomCode(){
        Integer code;
        Random random = new Random(System.currentTimeMillis());
        do {
            code = random.nextInt((9999 - 1000) + 1000);
        } while (classRepository.existsByCode(code));

        return code;
    }

    private Student checkStudent(String email){
        if (studentRepository.getAccountByEmail(email).isPresent()){
            return studentRepository.getAccountByEmail(email).get();
        }
        throw new ValidationException("Email é invalido!");
    }

    private Class checkClass(UUID id){
        if (classRepository.existsById(id)){
            return classRepository.getReferenceById(id);
        }
        throw new ValidationException("Id informado para Turma é invalido!");
    }

    private String generatePassword(RegisterStudent student){
        String name = student.account().getName().substring(0, 2);
        String socialSecurity = student.socialSecurity().substring(0, 3);
        return "#" + name + socialSecurity;
    }
}
