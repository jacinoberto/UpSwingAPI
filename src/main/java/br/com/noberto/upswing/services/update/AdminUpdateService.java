package br.com.noberto.upswing.services.update;

import br.com.noberto.upswing.dtos.academic.CourseRequest;
import br.com.noberto.upswing.dtos.address.AddressRequest;
import br.com.noberto.upswing.dtos.address.ZipCodeRequest;
import br.com.noberto.upswing.dtos.admin.AdminUpdate;
import br.com.noberto.upswing.dtos.student.StudentUpdate;
import br.com.noberto.upswing.enums.EducationLevel;
import br.com.noberto.upswing.models.*;
import br.com.noberto.upswing.repositories.*;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminUpdateService {
    private final AdminRepository adminRepository;
    private final StudentRepository studentRepository;
    private final ZipCodeRepository zipCodeRepository;
    private final AddressRepository addressRepository;
    private final ClassRepository classRepository;
    private final CourseRepository courseRepository;

    public AdminUpdateService(AdminRepository adminRepository, StudentRepository studentRepository, ZipCodeRepository zipCodeRepository, AddressRepository addressRepository, ClassRepository
            classRepository, CourseRepository courseRepository) {
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
        this.zipCodeRepository = zipCodeRepository;
        this.addressRepository = addressRepository;
        this.classRepository = classRepository;
        this.courseRepository = courseRepository;
    }

    public Admin updateAdmin(String adminId, AdminUpdate adminUpdate){
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new EntityExistsException("Administrador não encontrado!"));
        Account account = admin.getAccount();

        admin.setPosition(adminUpdate.position());
        account.setName(adminUpdate.account().getName());
        account.setEmail((adminUpdate.account().getEmail().equalsIgnoreCase(admin.getAccount().getEmail()))
                ? account.getEmail() : adminUpdate.account().getEmail());
        account.setMainPhone(adminUpdate.account().getMainPhone());
        account.setOptionalPhone(adminUpdate.account().getOptionalPhone());
        admin.setAccount(account);
        return admin;
    }

    public Student updateStudent(String studentId, StudentUpdate studentUpdate){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityExistsException("Aluno não encontrado!"));
        Account account = student.getAccount();
        Address address = addressUpdate(studentUpdate.address().id(), studentUpdate.address().zipCode(), studentUpdate.address());

        account.setName(studentUpdate.account().getName());
        account.setEmail(studentUpdate.account().getEmail());
        account.setOptionalPhone(student.getAccount().getMainPhone());
        account.setOptionalPhone(student.getAccount().getOptionalPhone());

        student.setAccount(account);
        student.setAddress(address);
        return student;
    }

    public Course updateCourse(UUID courseId, CourseRequest courseUpdate){
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityExistsException("Curso não encontrado!"));
        course.setCourseName(courseUpdate.courseName());
        course.setEducationLevel(EducationLevel.fromEducationLevel(courseUpdate.educationalLevel()));
        course.setSchedule(courseUpdate.schedule());
        course.setMonthlyCost(courseUpdate.monthlyCost());
        course.setTotalCost(courseUpdate.totalCost());
        return course;
    }

    public Address addressUpdate(UUID addressId, ZipCodeRequest zipCodeRequest, AddressRequest addressUpdate){
        ZipCode zipCode = new ZipCode();
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new EntityExistsException("Endereço não encontrado!"));

        if (zipCodeRepository.findById(zipCodeRequest.zipCode()).isPresent()){
            zipCode = zipCodeRepository.findById(zipCodeRequest.zipCode())
                            .orElseThrow(() -> new EntityExistsException("Cep não encontrado"));
            address.setZipCode(zipCode);
        } else {
            zipCode = zipCodeRepository.save(new ZipCode(zipCodeRequest));
        }
        address.setNumber(addressUpdate.number());
        address.setComplement(addressUpdate.complement());
        address.setZipCode(zipCode);
        return address;
    }
}
