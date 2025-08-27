package github.AlissonMartin.emailService.infrastructure.persistence.emailqueue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmailQueueRepository extends JpaRepository<EmailQueueEntity, Long> {

  @Query(value = """
    SELECT eq.* 
    FROM email_queue eq
    INNER JOIN (
        SELECT topic_id, MAX(id) as max_id
        FROM email_queue
        WHERE sent = false OR sent IS NULL
        GROUP BY topic_id
    ) latest ON eq.id = latest.max_id
  """, nativeQuery = true)
  List<EmailQueueEntity> findLastUnsentEmailsByAllTopics();

  @Query("""
    SELECT eq
    FROM EmailQueueEntity eq
    WHERE eq.sentAt = (
        SELECT MAX(eq2.sentAt)
        FROM EmailQueueEntity eq2
        WHERE eq2.topic.id = eq.topic.id
    )
  """)
  List<EmailQueueEntity> findLastSentEmailsByAllTopics();
}
