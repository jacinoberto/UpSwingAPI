package br.com.noberto.upswing.services.delete;

import br.com.noberto.upswing.enums.Status;
import br.com.noberto.upswing.models.*;
import br.com.noberto.upswing.repositories.AdminRepository;
import br.com.noberto.upswing.repositories.CompanyRepository;
import br.com.noberto.upswing.repositories.JobOfferRepository;
import br.com.noberto.upswing.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DisableService {
    private final AdminRepository repository;
    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final JobOfferRepository jobOfferRepository;

    @Autowired
    DisableService(AdminRepository repository, StudentRepository studentRepository, CompanyRepository companyRepository,
                   JobOfferRepository jobOfferRepository){
        this.repository = repository;
        this.studentRepository = studentRepository;
        this.companyRepository = companyRepository;
        this.jobOfferRepository = jobOfferRepository;
    }

    public void disableStudent(UUID id){
        Student student = studentRepository.getReferenceById(id);
        Account account = new Account(student);
        account.setActiveProfile(false);
        student.setAccount(account);
    }

    public void disableAdmin(UUID id){
        Admin admin = repository.getReferenceById(id);
        Account account = new Account(admin);
        account.setActiveProfile(false);
        admin.setAccount(account);
    }

    public void disableCompany(UUID id){
        Company company = companyRepository.getReferenceById(id);
        Account account = new Account(company);
        account.setActiveProfile(false);
        company.setAccount(account);
    }

    public void disableJobOffer(UUID id){
        JobOffer jobOffer = jobOfferRepository.getReferenceById(id);
        jobOffer.setStatus(Status.NOT_APPROVED);
    }
}
