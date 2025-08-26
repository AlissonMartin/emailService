package github.AlissonMartin.emailService.core.usecases.email;


import github.AlissonMartin.emailService.core.dto.SendEmailInput;


public interface SendEmailCase {

  public void execute(SendEmailInput input);
}
