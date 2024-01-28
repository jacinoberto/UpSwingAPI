package br.com.noberto.upswing.repositories;

import br.com.noberto.upswing.models.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClassRepository extends JpaRepository<Class, UUID> {
    boolean existsByCode(Integer code);
}
