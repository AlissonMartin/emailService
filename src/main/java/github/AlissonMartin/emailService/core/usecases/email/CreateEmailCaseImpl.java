package github.AlissonMartin.emailService.core.usecases.email;

import github.AlissonMartin.emailService.core.dto.CreateEmailInput;
import github.AlissonMartin.emailService.core.gateway.EmailGateway;

public class CreateEmailCaseImpl implements CreateEmailCase{
  private final EmailGateway emailGateway;

  public CreateEmailCaseImpl(EmailGateway emailGateway) {
    this.emailGateway = emailGateway;
  }

  @Override
  public void execute(CreateEmailInput input) {
    emailGateway.createEmail(input.to(), input.subject(), input.body());
  }
}
