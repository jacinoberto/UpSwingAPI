package br.com.noberto.upswing.repositories;

import br.com.noberto.upswing.models.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
    @Query("""
                SELECT c FROM Company c
                WHERE c.account.activeProfile = true
                AND c.status = APPROVED
            """)
    Page<Company> findAllActiveProfileTrue(Pageable pagination);

    @Query("""
                SELECT c FROM Company c
                WHERE c.status = PENDING
            """)
    Page<Company> findAllCompanyPending(Pageable pagination);
}
