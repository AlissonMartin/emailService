package github.AlissonMartin.emailService.infrastructure.gateway;

import github.AlissonMartin.emailService.core.entities.Email;
import github.AlissonMartin.emailService.core.entities.EmailQueue;
import github.AlissonMartin.emailService.core.entities.Topic;
import github.AlissonMartin.emailService.core.gateway.EmailQueueGateway;
import github.AlissonMartin.emailService.infrastructure.mapper.EmailMapper;
import github.AlissonMartin.emailService.infrastructure.mapper.EmailQueueMapper;
import github.AlissonMartin.emailService.infrastructure.mapper.TopicMapper;
import github.AlissonMartin.emailService.infrastructure.persistence.emailqueue.EmailQueueEntity;
import github.AlissonMartin.emailService.infrastructure.persistence.emailqueue.EmailQueueRepository;

import java.util.List;

public class EmailQueueRepositoryGateway implements EmailQueueGateway {
  private final EmailQueueRepository emailQueueRepository;
  private final EmailMapper emailMapper;
  private final TopicMapper topicMapper;
  private final EmailQueueMapper emailQueueMapper;

  public EmailQueueRepositoryGateway(EmailQueueRepository emailQueueRepository, EmailMapper emailMapper, TopicMapper topicMapper, EmailQueueMapper emailQueueMapper) {
    this.emailQueueRepository = emailQueueRepository;
    this.emailMapper = emailMapper;
    this.topicMapper = topicMapper;
    this.emailQueueMapper = emailQueueMapper;
  }

  @Override
  public void CreateEmailQueueEmail(Email email, Topic topic) {
    EmailQueueEntity emailQueueEntity = new EmailQueueEntity();

    emailQueueEntity.setEmail(emailMapper.toEntity(email));
    emailQueueEntity.setTopic(topicMapper.toEntity(topic));

    emailQueueRepository.save(emailQueueEntity);

  }

  @Override
  public List<EmailQueue> getLastUnsentEmails() {
    return emailQueueMapper.toDomainObjList(emailQueueRepository.findLastUnsentEmailsByAllTopics());
  }

  @Override
  public List<EmailQueue> getLastSentEmailsByTopic() {
    return emailQueueMapper.toDomainObjList(emailQueueRepository.findLastSentEmailsByAllTopics());
  }
}
