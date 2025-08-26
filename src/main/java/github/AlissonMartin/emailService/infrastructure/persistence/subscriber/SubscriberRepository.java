package github.AlissonMartin.emailService.infrastructure.persistence.subscriber;

import github.AlissonMartin.emailService.infrastructure.persistence.topic.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriberRepository extends JpaRepository<SubscriberEntity, Long> {

  Optional<SubscriberEntity> findByEmailAndTopic(String email, TopicEntity topic);

}
