package br.com.noberto.upswing.repositories;

import br.com.noberto.upswing.dtos.student.StudentResponse;
import br.com.noberto.upswing.models.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
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

    @Query("""
                SELECT s FROM Student s
                JOIN s.account a
                WHERE a.email = :email
            """)
    Optional<Student> getAccountByEmail(String email);

    @Query("""
                SELECT s FROM Student s
                JOIN s.autoApplies a
                WHERE a.student.id = :studentId
            """)
    Optional<Student> existsByStudent(String studentId);

    @Query("""
                SELECT s FROM Student s
                WHERE EXISTS(
                    SELECT c  FROM Company c
                    WHERE c.id = :companyId
                    AND c.address.zipCode.state = s.address.zipCode.state
                )
            """)
    List<Student> findByStateTrue(String companyId);

    @Query("""
                SELECT s FROM Student s
                WHERE EXISTS(
                    SELECT a FROM AutoApply a
                    WHERE a.enableAutoApply = true
                )
            """)
    List<Student> findByAutoApplyTrue();

    @Query("""
                SELECT s FROM Student s
                JOIN s.registrations r
                JOIN r.aClass cl
                JOIN cl.course co
                WHERE co.businessArea.id = :businessAreaId
            """)
    List<Student> findByBusinessArea(UUID businessAreaId);

    @Query("""
                SELECT s FROM Student s
                WHERE EXISTS(
                    SELECT r FROM Registration r
                    WHERE r.aClass.course.id = :courseId
                    AND s = r.student
                )
            """)
    List<Student> findByStudentsExistsInCourse(UUID courseId);

    @Query("""
                SELECT s FROM Student s
                WHERE EXISTS(
                    SELECT vo from VacancyOffer vo
                    WHERE vo.id = :vacancyOfferId
                    AND s = vo.student
                )
            """)
    Student findStudentByVacancyOffer(UUID vacancyOfferId);

    @Query("""
                SELECT s FROM Student s
                WHERE EXISTS(
                    SELECT vo from VacancyOffer vo
                    WHERE vo.jobOffer.id = :jobOfferId
                    AND s = vo.student
                )
            """)
    Page<Student> findStudentsByVacancyOffer(UUID jobOfferId, Pageable pagination);

    @Query("""
                SELECT s FROM Student s
                WHERE s.account.email = :email
            """)
    UserDetails findByEmail(String email);

    @Query("""
                SELECT s FROM Student s
                WHERE s.account.email = :email
            """)
    Optional<Student> findStudentByEmail(String email);
}
