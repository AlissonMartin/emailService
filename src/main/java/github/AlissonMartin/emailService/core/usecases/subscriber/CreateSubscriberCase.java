package github.AlissonMartin.emailService.core.usecases.subscriber;

import github.AlissonMartin.emailService.core.dto.CreateSubscriberInput;
import github.AlissonMartin.emailService.core.dto.CreateSubscriberOutput;

public interface CreateSubscriberCase {

  public CreateSubscriberOutput execute(CreateSubscriberInput input);
}
