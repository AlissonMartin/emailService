package github.AlissonMartin.emailService.core.usecases.topic;

import github.AlissonMartin.emailService.core.dto.CreateTopicInput;
import github.AlissonMartin.emailService.core.dto.CreateTopicOutput;
import github.AlissonMartin.emailService.core.entities.Topic;
import github.AlissonMartin.emailService.core.gateway.TopicGateway;

public class CreateTopicCaseImpl implements CreateTopicCase{
  public CreateTopicCaseImpl(TopicGateway topicGateway) {
    this.topicGateway = topicGateway;
  }

  private final TopicGateway topicGateway;


  @Override
  public CreateTopicOutput execute(CreateTopicInput input) {
    Topic topic = topicGateway.createTopic(input.name(), input.interval(), input.intervalUnit());

    return new CreateTopicOutput(topic.id(), topic.name(), topic.intervalValue(), topic.intervalUnit());
  }
}
