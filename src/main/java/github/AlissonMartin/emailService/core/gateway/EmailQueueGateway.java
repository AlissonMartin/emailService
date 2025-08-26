package github.AlissonMartin.emailService.core.gateway;

import github.AlissonMartin.emailService.core.entities.Email;
import github.AlissonMartin.emailService.core.entities.Topic;

public interface EmailQueueGateway {
  void CreateEmailQueueEmail(Email email, Topic topic);
}
