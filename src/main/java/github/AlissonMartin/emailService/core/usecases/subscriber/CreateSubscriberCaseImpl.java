package github.AlissonMartin.emailService.core.usecases.subscriber;

import github.AlissonMartin.emailService.core.dto.CreateSubscriberInput;
import github.AlissonMartin.emailService.core.dto.CreateSubscriberOutput;
import github.AlissonMartin.emailService.core.entities.Subscriber;
import github.AlissonMartin.emailService.core.entities.Topic;
import github.AlissonMartin.emailService.core.exception.EntityAlreadyExistsException;
import github.AlissonMartin.emailService.core.gateway.SubscriberGateway;
import github.AlissonMartin.emailService.core.gateway.TopicGateway;

public class CreateSubscriberCaseImpl implements CreateSubscriberCase {
  private SubscriberGateway subscriberGateway;
  private TopicGateway topicGateway;

  public CreateSubscriberCaseImpl(SubscriberGateway subscriberGateway, TopicGateway topicGateway) {
    this.subscriberGateway = subscriberGateway;
    this.topicGateway = topicGateway;
  }


  @Override
  public CreateSubscriberOutput execute(CreateSubscriberInput input) {
    Topic topic = topicGateway.getTopic(input.topic_id());
    if (subscriberGateway.getByEmailAndTopic(input.email(), topic) != null) throw new EntityAlreadyExistsException("Subscriber with the same topic already exists.");
    Subscriber subscriber = subscriberGateway.createSubscriber(input.email(), topic);

    return new CreateSubscriberOutput(subscriber.id(), subscriber.email(), subscriber.topic());
  }
}
