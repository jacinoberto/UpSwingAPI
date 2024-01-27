package br.com.noberto.upswing.repositories;

import br.com.noberto.upswing.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {
}
