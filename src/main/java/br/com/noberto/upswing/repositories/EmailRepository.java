package br.com.noberto.upswing.repositories;

import br.com.noberto.upswing.email.EmailSender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmailRepository extends JpaRepository<EmailSender, UUID> {
}
