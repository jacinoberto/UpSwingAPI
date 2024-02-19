package br.com.noberto.upswing.repositories;

import br.com.noberto.upswing.models.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    @Query("""
            SELECT c FROM Course c
            WHERE c.businessArea.id = :businessAreaId
            AND c.active = true
            """)
    Page<Course> findAllBusinessAreaById(UUID businessAreaId, Pageable pagination);

    @Query("""
            SELECT c FROM Course c
            WHERE c.businessArea.id = :businessAreaId
            AND c.active = true
            """)
    List<Course> findAllCourseBusinessAreaById(UUID businessAreaId);

    @Query("""
                SELECT c FROM Course c
                JOIN c.classes cl
                JOIN cl.registrations r
                JOIN r.student s
                WHERE s.id = :studentId
                AND c.active = true
            """)
    Page<Course> findAllStudentTrue(String studentId, Pageable pagination);

    @Query("""
                SELECT c FROM Course c
                JOIN c.classes cl
                JOIN cl.registrations r
                JOIN r.student s
                WHERE s.id = :studentId
                AND c.active = true
            """)
    List<Course> findAllCourseStudentTrue(UUID studentId);

    @Query("""
                SELECT c FROM Course c
                WHERE c.active = true
            """)
    Page<Course> findAllCourse(Pageable pagination);
}
