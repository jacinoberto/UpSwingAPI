package br.com.noberto.upswing.services.register;

import br.com.noberto.upswing.dtos.address.AddressRequest;
import br.com.noberto.upswing.dtos.address.ZipCodeRequest;
import br.com.noberto.upswing.dtos.admin.RegisterAdmin;
import br.com.noberto.upswing.dtos.student.RegisterStudent;
import br.com.noberto.upswing.models.Address;
import br.com.noberto.upswing.models.Admin;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.models.ZipCode;
import br.com.noberto.upswing.repositories.AddressRepository;
import br.com.noberto.upswing.repositories.AdminRepository;
import br.com.noberto.upswing.repositories.StudentRepository;
import br.com.noberto.upswing.repositories.ZipCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminRegisterService {
    private final AdminRepository repository;
    private final StudentRepository studentRepository;
    private final ZipCodeRepository zipCodeRepository;
    private final AddressRepository addressRepository;

    @Autowired
    AdminRegisterService(AdminRepository repository, StudentRepository studentRepository, ZipCodeRepository zipCodeRepository,
                         AddressRepository addressRepository){
        this.repository = repository;
        this.studentRepository = studentRepository;
        this.zipCodeRepository = zipCodeRepository;
        this.addressRepository = addressRepository;
    }

    public Student registerStudent(RegisterStudent registerStudent){
        Address address = checkAddress(registerStudent);
        Student student = new Student(registerStudent, address);
        return studentRepository.save(student);
    }

    public Admin registerAdmin(RegisterAdmin registerAdmin){
        Admin admin = new Admin(registerAdmin);
        return repository.save(admin);
    }

    //METHODS
    private Address checkAddress(RegisterStudent data){
        ZipCode zipCode = null;

        if (zipCodeRepository.existsById(data.zipCode())){
            zipCode = zipCodeRepository.getReferenceById(data.zipCode());
        } else {
            zipCode = zipCodeRepository.save(new ZipCode(data));
        }

        return addressRepository.save(new Address(data, zipCode));
    }
}
