package github.AlissonMartin.emailService.infrastructure.persistence.emailqueue;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailQueueRepository extends JpaRepository<EmailQueueEntity, Long> {
}
