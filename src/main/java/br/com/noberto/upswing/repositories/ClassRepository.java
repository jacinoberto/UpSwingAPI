package br.com.noberto.upswing.repositories;

import br.com.noberto.upswing.models.Class;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClassRepository extends JpaRepository<Class, UUID> {
    boolean existsByCode(Integer code);

    @Query("""
                SELECT c FROM Class c
                WHERE c.active = true
            """)
    Page<Class> findAllClass(Pageable pagination);
}
