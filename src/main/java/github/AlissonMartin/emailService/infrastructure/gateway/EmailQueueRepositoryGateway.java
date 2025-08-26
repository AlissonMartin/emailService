package github.AlissonMartin.emailService.infrastructure.gateway;

import github.AlissonMartin.emailService.core.entities.Email;
import github.AlissonMartin.emailService.core.entities.Topic;
import github.AlissonMartin.emailService.core.gateway.EmailQueueGateway;
import github.AlissonMartin.emailService.infrastructure.mapper.EmailMapper;
import github.AlissonMartin.emailService.infrastructure.mapper.TopicMapper;
import github.AlissonMartin.emailService.infrastructure.persistence.emailqueue.EmailQueueEntity;
import github.AlissonMartin.emailService.infrastructure.persistence.emailqueue.EmailQueueRepository;

public class EmailQueueRepositoryGateway implements EmailQueueGateway {
  private final EmailQueueRepository emailQueueRepository;
  private final EmailMapper emailMapper;
  private final TopicMapper topicMapper;

  public EmailQueueRepositoryGateway(EmailQueueRepository emailQueueRepository, EmailMapper emailMapper, TopicMapper topicMapper) {
    this.emailQueueRepository = emailQueueRepository;
    this.emailMapper = emailMapper;
    this.topicMapper = topicMapper;
  }

  @Override
  public void CreateEmailQueueEmail(Email email, Topic topic) {
    EmailQueueEntity emailQueueEntity = new EmailQueueEntity();

    emailQueueEntity.setEmail(emailMapper.toEntity(email));
    emailQueueEntity.setTopic(topicMapper.toEntity(topic));

    emailQueueRepository.save(emailQueueEntity);

  }
}
