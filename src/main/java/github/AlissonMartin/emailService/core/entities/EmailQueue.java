package github.AlissonMartin.emailService.core.entities;

import java.time.LocalDateTime;

public record EmailQueue(Long id, Topic topic, Email email, LocalDateTime sentAt) {

}
