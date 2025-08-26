package github.AlissonMartin.emailService.core.usecases.emailqueue;

import github.AlissonMartin.emailService.core.dto.CreateEmailQueueEmailInput;

public interface CreateEmailQueueEmailCase {
  void execute(CreateEmailQueueEmailInput input);
}
