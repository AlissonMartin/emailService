package github.AlissonMartin.emailService.infrastructure.gateway;

import github.AlissonMartin.emailService.core.entities.Subscriber;
import github.AlissonMartin.emailService.core.entities.Topic;
import github.AlissonMartin.emailService.core.gateway.SubscriberGateway;
import github.AlissonMartin.emailService.infrastructure.mapper.SubscriberMapper;
import github.AlissonMartin.emailService.infrastructure.mapper.TopicMapper;
import github.AlissonMartin.emailService.infrastructure.persistence.subscriber.SubscriberEntity;
import github.AlissonMartin.emailService.infrastructure.persistence.subscriber.SubscriberRepository;
import github.AlissonMartin.emailService.infrastructure.persistence.topic.TopicEntity;

import java.util.Optional;

public class SubscriberRepositoryGateway implements SubscriberGateway {
  private final SubscriberRepository subscriberRepository;
  private final TopicMapper topicMapper;
  private final SubscriberMapper subscriberMapper;

  public SubscriberRepositoryGateway(SubscriberRepository subscriberRepository, TopicMapper topicMapper, SubscriberMapper subscriberMapper) {
    this.subscriberRepository = subscriberRepository;
    this.topicMapper = topicMapper;
    this.subscriberMapper = subscriberMapper;
  }

  @Override
  public Subscriber createSubscriber(String email, Topic topicDomainObj) {
    SubscriberEntity subscriberEntity = new SubscriberEntity();
    subscriberEntity.setEmail(email);
    subscriberEntity.setTopic(topicMapper.toEntity(topicDomainObj));

    return subscriberMapper.toDomainObj(subscriberRepository.save(subscriberEntity));
  }

  @Override
  public Subscriber getByEmailAndTopic(String email, Topic topic) {
    TopicEntity topicEntity = topicMapper.toEntity(topic);
    Optional<SubscriberEntity> subscriberEntity = subscriberRepository.findByEmailAndTopic(email, topicEntity);

    if (subscriberEntity.isEmpty()) {
      return null;
    }
    return subscriberMapper.toDomainObj(subscriberEntity.get());
  }
}
