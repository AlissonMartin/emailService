package github.AlissonMartin.emailService.infrastructure.gateway;

import github.AlissonMartin.emailService.core.entities.Topic;
import github.AlissonMartin.emailService.core.enums.Interval;
import github.AlissonMartin.emailService.core.gateway.TopicGateway;
import github.AlissonMartin.emailService.infrastructure.mapper.TopicMapper;
import github.AlissonMartin.emailService.infrastructure.persistence.topic.TopicEntity;
import github.AlissonMartin.emailService.infrastructure.persistence.topic.TopicRepository;

public class TopicRepositoryGateway implements TopicGateway {

  private final TopicRepository topicRepository;
  private final TopicMapper topicMapper;

  public TopicRepositoryGateway(TopicRepository topicRepository, TopicMapper topicMapper) {
    this.topicRepository = topicRepository;
    this.topicMapper = topicMapper;
  }


  @Override
  public Topic getTopic(Long topicId) {
    return topicMapper.toDomainObj(topicRepository.findById(topicId).orElseThrow());
  }

  @Override
  public Topic createTopic(String name, int interval, Interval intervalUnit) {
    TopicEntity topicEntity = new TopicEntity();
    topicEntity.setName(name);
    topicEntity.setIntervalValue(interval);
    topicEntity.setIntervalUnit(intervalUnit);
    return topicMapper.toDomainObj(topicRepository.save(topicEntity));
  }
}
