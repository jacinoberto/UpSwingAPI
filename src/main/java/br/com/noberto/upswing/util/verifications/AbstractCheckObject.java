package br.com.noberto.upswing.util.verifications;

import br.com.noberto.upswing.dtos.address.AddressRequest;
import br.com.noberto.upswing.models.*;
import br.com.noberto.upswing.models.Class;
import br.com.noberto.upswing.repositories.*;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public abstract class AbstractCheckObject{
    protected final ZipCodeRepository zipCodeRepository;
    protected  final BusinessAreaRepository businessAreaRepository;
    protected final StudentRepository studentRepository;
    protected final ClassRepository classRepository;
    protected final CompanyRepository companyRepository;
    protected final CourseRepository courseRepository;

    @PersistenceContext
    private final EntityManager entityManager;

    public AbstractCheckObject(ZipCodeRepository zipCodeRepository, BusinessAreaRepository businessAreaRepository, StudentRepository studentRepository, ClassRepository classRepository, CompanyRepository companyRepository, CourseRepository courseRepository, EntityManager entityManager) {
        this.zipCodeRepository = zipCodeRepository;
        this.businessAreaRepository = businessAreaRepository;
        this.studentRepository = studentRepository;
        this.classRepository = classRepository;
        this.companyRepository = companyRepository;
        this.courseRepository = courseRepository;
        this.entityManager = entityManager;
    }

    public Address checkZipCode(AddressRequest address) {
        ZipCode zipCode = null;

        if (zipCodeRepository.existsById(address.zipCode().zipCode())){
            zipCode = zipCodeRepository.getReferenceById(address.zipCode().zipCode());
        } else {
            zipCode = zipCodeRepository.save(new ZipCode(address.zipCode()));
        }

        return new Address(address, zipCode);
    }

    @Transactional
    public BusinessArea checkBusinessArea(UUID businessAreaId){
        entityManager.flush();
        return businessAreaRepository.findById(businessAreaId)
                .orElseThrow(() -> new EntityExistsException("ID informado para Área de Atuação é invalido!"));
    }

    @Transactional
    public Class checkClass(UUID classId){
        entityManager.flush();
        return classRepository.findById(classId).
                orElseThrow(() -> new EntityExistsException("ID informado para Turma é invalido!"));
    }

    @Transactional
    public Company checkCompany(String companyId){
        entityManager.flush();
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityExistsException("ID informado para Empresa é invalido!"));
    }

    @Transactional
    public Course checkCourse(UUID courseId){
        entityManager.flush();
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityExistsException("ID informado para Curso é invalido!"));
    }

    @Transactional
    public Student checkStudent(String email){
        entityManager.flush();
        Student student = new Student();
        if (studentRepository.getAccountByEmail(email) != null){
            student =  studentRepository.getAccountByEmail(email)
                    .orElseThrow(() -> new EntityExistsException("Email informado para aluno é invalido!"));
        }
        if (studentRepository.findById(email).isPresent()){
            student = studentRepository.findById(email)
                    .orElseThrow(() -> new EntityExistsException("ID informado para Aluno é invalido!"));
        }

        return student;
    }
}
