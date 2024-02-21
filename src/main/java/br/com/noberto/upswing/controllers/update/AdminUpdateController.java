package br.com.noberto.upswing.controllers.update;

import br.com.noberto.upswing.dtos.academic.CourseRequest;
import br.com.noberto.upswing.dtos.admin.AdminResponse;
import br.com.noberto.upswing.dtos.admin.AdminUpdate;
import br.com.noberto.upswing.dtos.company.RegisterCompany;
import br.com.noberto.upswing.dtos.company.RegisterJobOffer;
import br.com.noberto.upswing.dtos.student.RegisterStudent;
import br.com.noberto.upswing.dtos.student.StudentUpdate;
import br.com.noberto.upswing.models.*;
import br.com.noberto.upswing.services.mail.EmailService;
import br.com.noberto.upswing.services.update.AdminUpdateService;
import br.com.noberto.upswing.services.update.ApprovalService;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/alter")
public class AdminUpdateController {
    private final AdminUpdateService service;
    private final ApprovalService approvalService;
    private final EmailService emailService;

    public AdminUpdateController(AdminUpdateService service, ApprovalService approvalService, EmailService emailService) {
        this.service = service;
        this.approvalService = approvalService;
        this.emailService = emailService;
    }

    @PatchMapping("/admin/{adminId}")
    @Transactional
    public ResponseEntity<AdminResponse> adminUpdate(@PathVariable String adminId, @RequestBody AdminUpdate adminUpdate){
        Admin admin = service.updateAdmin(adminId, adminUpdate);
        return ResponseEntity.ok(new AdminResponse(admin));
    }

    @PatchMapping("/student/{studentId}")
    @Transactional
    public ResponseEntity<RegisterStudent> studentUpdate(@PathVariable String studentId, @RequestBody StudentUpdate studentUpdate){
       Student student = service.updateStudent(studentId, studentUpdate);
        return ResponseEntity.ok(new RegisterStudent(student));
    }

    @PutMapping("/course/{courseId}")
    @Transactional
    public ResponseEntity<CourseRequest> courseUpdate(@PathVariable UUID courseId, @RequestBody CourseRequest courseUpdate){
        Course course = service.updateCourse(courseId, courseUpdate);
        return ResponseEntity.ok(new CourseRequest(course));
    }

    @PatchMapping("/job-approved/{jobOfferId}")
    public ResponseEntity<RegisterJobOffer> approvedJobOffer(@PathVariable UUID jobOfferId) throws MessagingException {
        JobOffer jobOffer = approvalService.approvedJobOffer(jobOfferId);
        emailService.emailForJobApplication(jobOffer);
        emailService.emailForApprovedVacancy(jobOffer);
        return ResponseEntity.ok(new RegisterJobOffer(jobOffer));
    }

    @PatchMapping("/job-not-approved/{jobOfferId}")
    public ResponseEntity<RegisterJobOffer> notApprovedJobOffer(@PathVariable UUID jobOfferId) throws MessagingException {
        JobOffer jobOffer = approvalService.notApprovedJobOffer(jobOfferId);
        emailService.emailForNotApprovedVacancy(jobOffer);
        return ResponseEntity.ok(new RegisterJobOffer(jobOffer));
    }

    @PatchMapping("/company-approved/{companyId}")
    public ResponseEntity<RegisterCompany> approvedCompany(@PathVariable String companyId) throws MessagingException {
        Company company = approvalService.approvedProfile(companyId);
        emailService.emailForApprovedProfile(company);
        return ResponseEntity.ok(new RegisterCompany(company));
    }

    @PatchMapping("/company-not-approved/{companyId}")
    public ResponseEntity<RegisterCompany> notApprovedCompany(@PathVariable String companyId) throws MessagingException {
        Company company = approvalService.notApprovedProfile(companyId);
        emailService.emailForNotApprovedProfile(company);
        return ResponseEntity.ok(new RegisterCompany(company));
    }
}
