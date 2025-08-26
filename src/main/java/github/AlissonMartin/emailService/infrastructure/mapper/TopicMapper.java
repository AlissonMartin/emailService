package github.AlissonMartin.emailService.infrastructure.mapper;

import github.AlissonMartin.emailService.core.dto.CreateEmailInput;
import github.AlissonMartin.emailService.core.dto.CreateEmailQueueEmailInput;
import github.AlissonMartin.emailService.core.dto.CreateTopicInput;
import github.AlissonMartin.emailService.core.dto.CreateTopicOutput;
import github.AlissonMartin.emailService.core.entities.Topic;
import github.AlissonMartin.emailService.infrastructure.dto.emailqueue.CreateEmailQueueEmailRequest;
import github.AlissonMartin.emailService.infrastructure.dto.topic.CreateTopicRequest;
import github.AlissonMartin.emailService.infrastructure.dto.topic.CreateTopicResponse;
import github.AlissonMartin.emailService.infrastructure.persistence.topic.TopicEntity;

public class TopicMapper {

  public TopicEntity toEntity(Topic TopicDomainObj) {
    return new TopicEntity(TopicDomainObj.id(), TopicDomainObj.name(), TopicDomainObj.intervalValue(), TopicDomainObj.intervalUnit());
  }

  public Topic toDomainObj(TopicEntity topicEntity) {
    return new Topic(topicEntity.getId(), topicEntity.getName(), topicEntity.getIntervalValue(), topicEntity.getIntervalUnit());
  }

  public CreateTopicInput toInput(CreateTopicRequest createTopicRequest) {
    return new CreateTopicInput(createTopicRequest.name(), createTopicRequest.interval(), createTopicRequest.intervalUnit());
  }

  public CreateTopicResponse toResponse(CreateTopicOutput createTopicOutput) {
    return new CreateTopicResponse(createTopicOutput.id(), createTopicOutput.name(), createTopicOutput.interval(), createTopicOutput.intervalUnit());
  }

  public CreateEmailQueueEmailInput toCreateEmailQueueInput(CreateEmailQueueEmailRequest request, Long topic_id) {
    CreateEmailInput input = new CreateEmailInput(null, request.subject(), request.body());
    return new CreateEmailQueueEmailInput(input, topic_id);
  }

}
