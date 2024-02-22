package br.com.noberto.upswing.controllers.update;

import br.com.noberto.upswing.dtos.company.UnapplyVacancy;
import br.com.noberto.upswing.dtos.company.VacancyOfferResponse;
import br.com.noberto.upswing.dtos.student.SocialNetworksUpdate;
import br.com.noberto.upswing.dtos.student.StudentResponse;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.services.update.StudentUpdateService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/alter")
public class StudentUpdateController {
    private final StudentUpdateService service;

    public StudentUpdateController(StudentUpdateService service) {
        this.service = service;
    }

    @PatchMapping("/social-networks/{studentId}")
    @Transactional
    public ResponseEntity<StudentResponse> studentUpdateSocialNetworks(@PathVariable String studentId, @RequestBody SocialNetworksUpdate socialNetworksUpdate){
        Student student = service.studentUpdateSocialNetworks(studentId, socialNetworksUpdate);
        return ResponseEntity.ok(new StudentResponse(student));
    }

    @PatchMapping("/unapply-vacancy")
    @Transactional
    public ResponseEntity<VacancyOfferResponse> studentAlterApplicationVacancy(@RequestBody UnapplyVacancy unapplyVacancy){
        return ResponseEntity.ok(new VacancyOfferResponse(service.studentAlterApplicationVacancy(unapplyVacancy)));
    }
}
