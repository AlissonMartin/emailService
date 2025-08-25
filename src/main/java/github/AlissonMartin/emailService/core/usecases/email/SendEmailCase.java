package github.AlissonMartin.emailService.core.usecases.email;


import github.AlissonMartin.emailService.core.dto.SendEmailInput;

import java.util.List;

public interface SendEmailCase {

  public void execute(SendEmailInput input);
}
