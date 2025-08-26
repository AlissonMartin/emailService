package github.AlissonMartin.emailService.infrastructure.persistence.topic;

import github.AlissonMartin.emailService.core.enums.Interval;
import github.AlissonMartin.emailService.infrastructure.persistence.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "topics")
public class TopicEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private int intervalValue;

  private Interval intervalUnit;
}
