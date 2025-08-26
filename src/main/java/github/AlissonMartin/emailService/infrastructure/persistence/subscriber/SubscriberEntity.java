package github.AlissonMartin.emailService.infrastructure.persistence.subscriber;

import github.AlissonMartin.emailService.infrastructure.persistence.BaseEntity;
import github.AlissonMartin.emailService.infrastructure.persistence.topic.TopicEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "subscribers")
@Data
public class SubscriberEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;

  @ManyToOne
  @JoinColumn(name = "topic_id")
  private TopicEntity topic;

}
