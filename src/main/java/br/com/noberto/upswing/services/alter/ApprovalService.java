package br.com.noberto.upswing.services.alter;

import br.com.noberto.upswing.enums.Status;
import br.com.noberto.upswing.models.Company;
import br.com.noberto.upswing.models.JobOffer;
import br.com.noberto.upswing.repositories.CompanyRepository;
import br.com.noberto.upswing.repositories.JobOfferRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ApprovalService {
    private final CompanyRepository companyRepository;
    private final JobOfferRepository jobOfferRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ApprovalService(CompanyRepository companyRepository, JobOfferRepository jobOfferRepository) {
        this.companyRepository = companyRepository;
        this.jobOfferRepository = jobOfferRepository;
    }

    @Transactional
    public Company approvedProfile(UUID companyId){
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityExistsException("ID informado para Empresa é invalido"));
        company.setStatus(Status.APPROVED);
        entityManager.flush();
        return company;
    }

    @Transactional
    public Company notApprovedProfile(UUID companyId){
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
