package br.com.noberto.upswing.repositories;

import br.com.noberto.upswing.models.CompletedSubject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompletedSubjectRepository extends JpaRepository<CompletedSubject, UUID> {
}
