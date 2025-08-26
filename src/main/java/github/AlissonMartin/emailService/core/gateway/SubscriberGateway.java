package github.AlissonMartin.emailService.core.gateway;

import github.AlissonMartin.emailService.core.entities.Subscriber;
import github.AlissonMartin.emailService.core.entities.Topic;

public interface SubscriberGateway {
  Subscriber createSubscriber(String email, Topic topic);
  Subscriber getByEmailAndTopic(String email, Topic topic);
}
