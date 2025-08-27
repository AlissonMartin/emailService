package github.AlissonMartin.emailService.core.gateway;

import github.AlissonMartin.emailService.core.entities.Email;
import github.AlissonMartin.emailService.core.entities.EmailQueue;
import github.AlissonMartin.emailService.core.entities.Topic;

import java.util.List;

public interface EmailQueueGateway {
  void CreateEmailQueueEmail(Email email, Topic topic);
  List<EmailQueue> getLastUnsentEmails();
  List<EmailQueue> getLastSentEmailsByTopic();

}
