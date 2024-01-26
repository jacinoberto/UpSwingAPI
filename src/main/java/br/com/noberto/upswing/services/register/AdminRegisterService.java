package br.com.noberto.upswing.services.register;

import br.com.noberto.upswing.dtos.address.AddressRrequest;
import br.com.noberto.upswing.dtos.address.ZipCodeRequest;
import br.com.noberto.upswing.dtos.student.RegisterStudent;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminRegisterService {
    private final StudentRepository studentRepository;

    @Autowired
    AdminRegisterService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student registerStudent(RegisterStudent data){
        ZipCodeRequest zipCode = new ZipCodeRequest(data.zipCode(), data.street(), data.area(), data.city(), data.state());
        AddressRrequest address = new AddressRrequest(data.number(), data.complement(), zipCode);
        Student student = new Student(data, address);
        return studentRepository.save(student);
    }
}
