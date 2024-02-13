package br.com.noberto.upswing.controllers.update;

import br.com.noberto.upswing.dtos.academic.CourseRequest;
import br.com.noberto.upswing.dtos.address.AddressRequest;
import br.com.noberto.upswing.dtos.admin.AdminResponse;
import br.com.noberto.upswing.dtos.admin.AdminUpdate;
import br.com.noberto.upswing.dtos.student.RegisterStudent;
import br.com.noberto.upswing.dtos.student.StudentResponse;
import br.com.noberto.upswing.dtos.student.StudentUpdate;
import br.com.noberto.upswing.models.Address;
import br.com.noberto.upswing.models.Admin;
import br.com.noberto.upswing.models.Course;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.services.update.AdminUpdateService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/alter")
public class AdminUpdateController {
    private final AdminUpdateService service;

    public AdminUpdateController(AdminUpdateService service) {
        this.service = service;
    }

    @PatchMapping("/admin/{adminId}")
    @Transactional
    public ResponseEntity<AdminResponse> adminUpdate(@PathVariable UUID adminId, @RequestBody AdminUpdate adminUpdate){
        Admin admin = service.updateAdmin(adminId, adminUpdate);
        return ResponseEntity.ok(new AdminResponse(admin));
    }

    @PatchMapping("/student/{studentId}")
    @Transactional
    public ResponseEntity<RegisterStudent> studentUpdate(@PathVariable UUID studentId, @RequestBody StudentUpdate studentUpdate){
       Student student = service.updateStudent(studentId, studentUpdate);
        return ResponseEntity.ok(new RegisterStudent(student));
    }

    @PutMapping("/course/{courseId}")
    @Transactional
    public ResponseEntity<CourseRequest> courseUpdate(@PathVariable UUID courseId, @RequestBody CourseRequest courseUpdate){
        Course course = service.updateCourse(courseId, courseUpdate);
        return ResponseEntity.ok(new CourseRequest(course));
    }

//    @PatchMapping("/address/{addressId}")
//    @Transactional
//    public ResponseEntity<AddressRequest> addressUpdate(@PathVariable UUID addressId, @RequestBody AddressRequest addressRequest){
//        Address address = service.addressUpdate(addressId, addressRequest.zipCode());
//        return ResponseEntity.ok(new AddressRequest(address));
//    }
}
