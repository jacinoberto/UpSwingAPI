package br.com.noberto.upswing.repositories;

import br.com.noberto.upswing.models.Course;
import br.com.noberto.upswing.models.VacancyAndCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VacancyAndCourseRepository extends JpaRepository<VacancyAndCourse, UUID> {

    @Query("""
                SELECT c FROM Course c
                WHERE EXISTS(
                    SELECT vc FROM VacancyAndCourse vc
                    WHERE vc.jobOffer.id = :jobOfferId
                    AND c = vc.course
                )
            """)
    List<Course> findByCoursesExistsInVacancyAndCourse(UUID jobOfferId);
}
