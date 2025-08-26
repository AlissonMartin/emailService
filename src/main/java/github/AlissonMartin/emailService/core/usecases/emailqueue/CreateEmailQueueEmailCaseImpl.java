package github.AlissonMartin.emailService.core.usecases.emailqueue;

import github.AlissonMartin.emailService.core.dto.CreateEmailQueueEmailInput;
import github.AlissonMartin.emailService.core.entities.Email;
import github.AlissonMartin.emailService.core.entities.Topic;
import github.AlissonMartin.emailService.core.exception.EntityNotFoundException;
import github.AlissonMartin.emailService.core.gateway.EmailQueueGateway;
import github.AlissonMartin.emailService.core.gateway.TopicGateway;
import github.AlissonMartin.emailService.core.usecases.email.CreateEmailCase;

public class CreateEmailQueueEmailCaseImpl implements CreateEmailQueueEmailCase{
  private final CreateEmailCase createEmailCase;
  private final TopicGateway topicGateway;
  private final EmailQueueGateway emailQueueGateway;

  public CreateEmailQueueEmailCaseImpl(CreateEmailCase createEmailCase, EmailQueueGateway emailQueueGateway, TopicGateway topicGateway) {
    this.createEmailCase = createEmailCase;
    this.emailQueueGateway = emailQueueGateway;
    this.topicGateway = topicGateway;
  }

  @Override
  public void execute(CreateEmailQueueEmailInput input) {
    Topic topic = topicGateway.getTopic(input.topic_id());
    if (topic == null) throw new EntityNotFoundException("Topic not found.");

    Email email = createEmailCase.execute(input.createEmailInput());

    emailQueueGateway.CreateEmailQueueEmail(email, topic);
  }
}
