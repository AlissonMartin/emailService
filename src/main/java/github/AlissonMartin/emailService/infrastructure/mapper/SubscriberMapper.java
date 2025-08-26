package github.AlissonMartin.emailService.infrastructure.mapper;

import github.AlissonMartin.emailService.core.dto.CreateSubscriberOutput;
import github.AlissonMartin.emailService.core.entities.Subscriber;
import github.AlissonMartin.emailService.infrastructure.dto.subscriber.CreateSubscriberResponse;
import github.AlissonMartin.emailService.infrastructure.persistence.subscriber.SubscriberEntity;

public class SubscriberMapper {
  public Subscriber toDomainObj(SubscriberEntity subscriberEntity) {
    return new Subscriber(subscriberEntity.getId(), subscriberEntity.getEmail(), new TopicMapper().toDomainObj(subscriberEntity.getTopic()));
  }

  public CreateSubscriberResponse toResponseObj(CreateSubscriberOutput createSubscriberOutput) {
    return new CreateSubscriberResponse(createSubscriberOutput.id(), createSubscriberOutput.email(), createSubscriberOutput.topic());
  }

}
