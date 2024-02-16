package br.com.noberto.upswing.repositories;

import br.com.noberto.upswing.models.AutoApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AutoApplyRepository extends JpaRepository<AutoApply, UUID> {

    @Query("""
                SELECT a FROM AutoApply a
                WHERE a.student.id = :studentId
            """)
    AutoApply findByStudentPresentAutoApply(String studentId);
}
