package github.AlissonMartin.emailService.core.usecases.email;

import github.AlissonMartin.emailService.core.dto.CreateEmailInput;
import github.AlissonMartin.emailService.core.entities.Email;

public interface CreateEmailCase {
  public Email execute(CreateEmailInput input);
}
