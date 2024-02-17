package br.com.noberto.upswing.repositories;

import br.com.noberto.upswing.models.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

    @Query("SELECT a FROM Admin a WHERE a.account.activeProfile = true")
    Page<Admin> findAllActiveProfileTrue(Pageable pagination);

    @Query("""
                SELECT a FROM Admin a
                WHERE a.account.email = :email
            """)
    UserDetails findByEmail(String email);

    @Query("""
                SELECT a FROM Admin a
                WHERE a.account.email = :email
            """)
    Optional<Admin> findAdminByEmail(String email);
}
