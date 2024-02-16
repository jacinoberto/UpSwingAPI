package br.com.noberto.upswing.services.update;

import br.com.noberto.upswing.enums.Status;
import br.com.noberto.upswing.models.*;
import br.com.noberto.upswing.models.Class;
import br.com.noberto.upswing.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DisableService {
    private final AdminRepository repository;
    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final JobOfferRepository jobOfferRepository;
    private final ClassRepository classRepository;
    private final CourseRepository courseRepository;

    @Autowired
    DisableService(AdminRepository repository, StudentRepository studentRepository, CompanyRepository companyRepository,
                   JobOfferRepository jobOfferRepository, ClassRepository classRepository, CourseRepository courseRepository){
        this.repository = repository;
        this.studentRepository = studentRepository;
        this.companyRepository = companyRepository;
        this.jobOfferRepository = jobOfferRepository;
        this.classRepository = classRepository;
        this.courseRepository = courseRepository;
    }

    public void disableStudent(String id){
        Student student = studentRepository.getReferenceById(id);
        Account account = new Account(student);
        account.setActiveProfile(false);
        student.setAccount(account);
    }

    public void disableAdmin(String id){
        Admin admin = repository.getReferenceById(id);
        Account account = new Account(admin);
        account.setActiveProfile(false);
        admin.setAccount(account);
    }

    public void disableCompany(String id){
        Company company = companyRepository.getReferenceById(id);
        Account account = new Account(company);
        account.setActiveProfile(false);
        company.setAccount(account);
    }

    public void disableJobOffer(UUID id){
        JobOffer jobOffer = jobOfferRepository.getReferenceById(id);
        jobOffer.setStatus(Status.NOT_APPROVED);
    }

    public void disableClass(UUID id){
        Class aClass = classRepository.getReferenceById(id);
        aClass.setActive(false);
    }

    public void disableCourse(UUID id){
        Course course = courseRepository.getReferenceById(id);
        course.setActive(false);
    }
}
