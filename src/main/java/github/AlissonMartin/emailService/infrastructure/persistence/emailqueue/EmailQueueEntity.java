package github.AlissonMartin.emailService.infrastructure.persistence.emailqueue;


import github.AlissonMartin.emailService.infrastructure.persistence.email.EmailEntity;
import github.AlissonMartin.emailService.infrastructure.persistence.topic.TopicEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "email_queue")
@Data
public class EmailQueueEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "topic_id")
  private TopicEntity topic;

  @ManyToOne
  @JoinColumn(name = "email_id")
  private EmailEntity email;

  private LocalDateTime sentAt;
}
