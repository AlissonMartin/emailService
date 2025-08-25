package github.AlissonMartin.emailService.core.usecases.email;

import github.AlissonMartin.emailService.core.dto.CreateEmailInput;

public interface CreateEmailCase {
  public void execute(CreateEmailInput input);
}
