package br.com.noberto.upswing.util.verifications;

import br.com.noberto.upswing.dtos.address.AddressRequest;
import br.com.noberto.upswing.models.*;
import br.com.noberto.upswing.models.Class;
import br.com.noberto.upswing.repositories.*;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AdminCheck implements ICheckObjectStrategy{
    private final BusinessAreaRepository businessAreaRepository;
    private final ZipCodeRepository zipCodeRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;

    public AdminCheck(BusinessAreaRepository businessAreaRepository, ZipCodeRepository zipCodeRepository, CourseRepository
            courseRepository, StudentRepository studentRepository, ClassRepository classRepository) {
        this.businessAreaRepository = businessAreaRepository;
        this.zipCodeRepository = zipCodeRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.classRepository = classRepository;
    }


    @Override
    public Address checkZipCode(AddressRequest address) {
        ZipCode zipCode = null;

        if (zipCodeRepository.existsById(address.zipCode().zipCode())){
            zipCode = zipCodeRepository.getReferenceById(address.zipCode().zipCode());
        } else {
            zipCode = zipCodeRepository.save(new ZipCode(address.zipCode()));
        }

        return new Address(address, zipCode);
    }

    @Override
    public BusinessArea checkBusinessArea(UUID businessAreaId) {
        if (businessAreaRepository.existsById(businessAreaId)){
            return businessAreaRepository.getReferenceById(businessAreaId);
        }

        throw new ValidationException("ID informado para Área de Atuação é invalido !");
    }

    public Course checkCourse(UUID id){
        if (courseRepository.existsById(id)){
            return courseRepository.getReferenceById(id);
        }
        throw new ValidationException("ID informado para Curso é invalido");
    }

    public Student checkStudent(String email){
        if (studentRepository.getAccountByEmail(email).isPresent()){
            return studentRepository.getAccountByEmail(email).get();
        }
        throw new ValidationException("Email é invalido!");
    }

    public Class checkClass(UUID id){
        if (classRepository.existsById(id)){
            return classRepository.getReferenceById(id);
        }
        throw new ValidationException("Id informado para Turma é invalido!");
    }
}
