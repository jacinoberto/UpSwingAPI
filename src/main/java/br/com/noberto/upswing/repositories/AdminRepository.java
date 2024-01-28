package br.com.noberto.upswing.repositories;

import br.com.noberto.upswing.models.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {

    @Query("SELECT a FROM Admin a WHERE a.account.activeProfile = true")
    Page<Admin> findAllActiveProfileTrue(Pageable pagination);
}
