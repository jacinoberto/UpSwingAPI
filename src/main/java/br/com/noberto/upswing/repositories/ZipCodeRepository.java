package br.com.noberto.upswing.repositories;

import br.com.noberto.upswing.models.ZipCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipCodeRepository extends JpaRepository<ZipCode, String> {
}
