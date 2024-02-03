package br.com.noberto.upswing.repositories;

import br.com.noberto.upswing.models.JobOffer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, UUID> {

    @Query("""
                SELECT j FROM JobOffer j
                JOIN j.vacancyOffers v
                JOIN v.student s
                WHERE s.id = :studentId
                AND j.closingDate >= :date
                AND j.status = APPROVED
            """)
    Page<JobOffer> findByStudentTrue(UUID studentId, LocalDate date, Pageable pagination);

    @Query("""
                SELECT jo FROM JobOffer jo
                JOIN jo.company c
                WHERE c.id = :companyId
                AND jo.status = APPROVED
            """)
    Page<JobOffer> findAllMyVacancies(UUID companyId, Pageable pagination);
}
