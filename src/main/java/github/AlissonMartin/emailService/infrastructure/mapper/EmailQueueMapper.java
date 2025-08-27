package github.AlissonMartin.emailService.infrastructure.mapper;

import github.AlissonMartin.emailService.core.entities.EmailQueue;
import github.AlissonMartin.emailService.infrastructure.persistence.emailqueue.EmailQueueEntity;

import java.util.List;
import java.util.stream.Collectors;

public class EmailQueueMapper {
  private final EmailMapper emailMapper;
  private final TopicMapper topicMapper;

  public EmailQueueMapper(EmailMapper emailMapper, TopicMapper topicMapper) {
    this.emailMapper = emailMapper;
    this.topicMapper = topicMapper;
  }

  public EmailQueue toDomainObj(EmailQueueEntity emailQueueEntity) {
    return new EmailQueue(emailQueueEntity.getId(), topicMapper.toDomainObj(emailQueueEntity.getTopic()), emailMapper.toDomainObj(emailQueueEntity.getEmail()), emailQueueEntity.getSentAt());
  }

  public List<EmailQueue> toDomainObjList(List<EmailQueueEntity> emailQueueEntities) {
    return emailQueueEntities.stream().map(this::toDomainObj).collect(Collectors.toList());
  }
}
