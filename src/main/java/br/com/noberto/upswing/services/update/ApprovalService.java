package br.com.noberto.upswing.services.update;

import br.com.noberto.upswing.enums.Status;
import br.com.noberto.upswing.models.Company;
import br.com.noberto.upswing.models.JobOffer;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.models.VacancyOffer;
import br.com.noberto.upswing.repositories.CompanyRepository;
import br.com.noberto.upswing.repositories.JobOfferRepository;
import br.com.noberto.upswing.repositories.VacancyOfferRepository;
import br.com.noberto.upswing.util.filters.FilterStudentsByContractTypeStrategy;
import br.com.noberto.upswing.util.filters.IFilterStudentStrategy;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ApprovalService {
    private final CompanyRepository companyRepository;
    private final JobOfferRepository jobOfferRepository;
    private final VacancyOfferRepository vacancyOfferRepository;
    private final IFilterStudentStrategy filterStudent;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ApprovalService(CompanyRepository companyRepository, JobOfferRepository jobOfferRepository, VacancyOfferRepository vacancyOfferRepository, FilterStudentsByContractTypeStrategy filterStudent) {
        this.companyRepository = companyRepository;
        this.jobOfferRepository = jobOfferRepository;
        this.vacancyOfferRepository = vacancyOfferRepository;
        this.filterStudent = filterStudent;
    }

    @Transactional
    public Company approvedProfile(String companyId){
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityExistsException("ID informado para Empresa é invalido"));
        company.setStatus(Status.APPROVED);
        entityManager.flush();
        return company;
    }

    @Transactional
    public Company notApprovedProfile(String companyId){
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityExistsException("ID informado para Empresa é invalido"));
        company.setStatus(Status.NOT_APPROVED);
        entityManager.flush();
        return company;
    }

    @Transactional
    public JobOffer approvedJobOffer(UUID jobOfferId){
        JobOffer jobOffer = jobOfferRepository.findById(jobOfferId)
                .orElseThrow(() -> new EntityExistsException("ID informado para Vaga é invalido"));
        jobOffer.setStatus(Status.APPROVED);
        List<Student> students = filterStudent.filterStudents(jobOffer);
        for (Student student : students) {
           vacancyOfferRepository.save(new VacancyOffer(student, jobOffer));
        }
        entityManager.flush();
        return jobOffer;
    }

    @Transactional
    public JobOffer notApprovedJobOffer(UUID jobOfferId){
        JobOffer jobOffer = jobOfferRepository.findById(jobOfferId)
                .orElseThrow(() -> new EntityExistsException("ID informado para Vaga é invalido"));
        jobOffer.setStatus(Status.NOT_APPROVED);
        entityManager.flush();
        return jobOffer;
    }
}
