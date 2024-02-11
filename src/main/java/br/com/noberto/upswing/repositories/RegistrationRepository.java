package br.com.noberto.upswing.repositories;

import br.com.noberto.upswing.models.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
    boolean existsByRegistration(Integer registration);
}
