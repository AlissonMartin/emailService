package github.AlissonMartin.emailService.core.usecases.topic;


import github.AlissonMartin.emailService.core.dto.CreateTopicInput;
import github.AlissonMartin.emailService.core.dto.CreateTopicOutput;

public interface CreateTopicCase {
  public CreateTopicOutput execute(CreateTopicInput input);
}
