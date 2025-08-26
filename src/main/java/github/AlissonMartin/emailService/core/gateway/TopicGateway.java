package github.AlissonMartin.emailService.core.gateway;

import github.AlissonMartin.emailService.core.entities.Topic;
import github.AlissonMartin.emailService.core.enums.Interval;

public interface TopicGateway {
  Topic getTopic(Long id);
  Topic createTopic(String name, int interval, Interval intervalUnit);
}
