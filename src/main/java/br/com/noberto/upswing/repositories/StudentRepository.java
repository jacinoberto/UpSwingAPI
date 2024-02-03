package br.com.noberto.upswing.repositories;

import br.com.noberto.upswing.dtos.student.StudentResponse;
import br.com.noberto.upswing.models.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    @Query("""
            SELECT s FROM Student s
            WHERE s.account.activeProfile = true
            """)
    Page<Student> findAllActiveProfileTrue(Pageable pagination);

    @Query("""
                SELECT s FROM Student s
                JOIN s.account a
                WHERE a.activeProfile = true
            """)
    Page<Student> findAllStudent(Pageable pagination);
}
