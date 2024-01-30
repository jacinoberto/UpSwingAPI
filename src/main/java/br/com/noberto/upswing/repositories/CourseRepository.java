package br.com.noberto.upswing.repositories;

import br.com.noberto.upswing.models.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    @Query("SELECT c FROM Course c WHERE c.businessArea.id = :id")
    Page<Course> findAllBusinessAreaById(UUID id, Pageable pagination);

    @Query("""
                SELECT c FROM Course c
                JOIN c.classes cl
                JOIN cl.registrations r
                JOIN r.student s
                WHERE s.id = :id
            """)
    Page<Course> findAllCourseStudentTrue(UUID id, Pageable pagination);
}
