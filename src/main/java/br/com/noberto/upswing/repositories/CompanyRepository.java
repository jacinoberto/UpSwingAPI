package br.com.noberto.upswing.repositories;

import br.com.noberto.upswing.models.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
    @Query("""
                SELECT c FROM Company c
                WHERE c.status = PENDING
                AND c.account.activeProfile = true
            """)
    Page<Company> findAllCompanyPending(Pageable pagination);

    @Query("""
                SELECT c FROM Company c
                WHERE c.status = APPROVED
                AND c.account.activeProfile = true
            """)
    Page<Company> findAllCompanyApproved(Pageable pagination);

    @Query("""
                SELECT c FROM Company c
                WHERE c.account.email = :email
            """)
    UserDetails findByEmail(String email);

    @Query("""
                SELECT c FROM Company c
                WHERE c.account.email = :email
            """)
    Optional<Company> findCompanyByEmail(String email);
}
