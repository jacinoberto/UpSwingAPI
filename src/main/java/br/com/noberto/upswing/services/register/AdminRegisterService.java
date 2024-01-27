package br.com.noberto.upswing.services.register;

import br.com.noberto.upswing.dtos.address.AddressRrequest;
import br.com.noberto.upswing.dtos.address.ZipCodeRequest;
import br.com.noberto.upswing.dtos.admin.RegisterAdmin;
import br.com.noberto.upswing.dtos.student.RegisterStudent;
import br.com.noberto.upswing.models.Admin;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.repositories.AdminRepository;
import br.com.noberto.upswing.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminRegisterService {
    private final AdminRepository repository;
    private final StudentRepository studentRepository;

    @Autowired
    AdminRegisterService(AdminRepository repository, StudentRepository studentRepository){
        this.repository = repository;
        this.studentRepository = studentRepository;
    }

    public Student registerStudent(RegisterStudent data){
        ZipCodeRequest zipCode = new ZipCodeRequest(data.zipCode(), data.street(), data.area(), data.city(), data.state());
        AddressRrequest address = new AddressRrequest(data.number(), data.complement(), zipCode);
        Student student = new Student(data, address);
        return studentRepository.save(student);
    }

    public Admin registerAdmin(RegisterAdmin data){
        Admin admin = new Admin(data);
        return repository.save(admin);
    }
}
