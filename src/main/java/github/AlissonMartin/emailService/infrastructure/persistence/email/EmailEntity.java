package github.AlissonMartin.emailService.infrastructure.persistence.email;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "emails")
@Data
public class EmailEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String address;

  private String subject;

  private String body;

  @Column(updatable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;

}
