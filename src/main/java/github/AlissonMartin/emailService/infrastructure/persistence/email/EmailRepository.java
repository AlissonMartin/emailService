package github.AlissonMartin.emailService.infrastructure.persistence.email;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailEntity, Long> {
}
