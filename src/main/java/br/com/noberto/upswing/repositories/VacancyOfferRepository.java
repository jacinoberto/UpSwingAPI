package br.com.noberto.upswing.repositories;


import br.com.noberto.upswing.models.VacancyOffer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VacancyOfferRepository extends JpaRepository<VacancyOffer, UUID> {
    @Query("""
                SELECT vo FROM VacancyOffer vo
                JOIN vo.jobOffer jo
                JOIN jo.company c
                WHERE c.id = :companyId
           """)
    Page<VacancyOffer> findAllCandidates(String companyId, Pageable pagination);

    @Query("""
                SELECT vo FROM VacancyOffer vo
                WHERE vo.jobOffer.id = :jobOfferId
                AND vo.student.id = :studentId
           """)
    VacancyOffer findByJobOffer(UUID jobOfferId, String studentId);
}
