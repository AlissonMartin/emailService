package github.AlissonMartin.emailService.core.usecases.email;

import github.AlissonMartin.emailService.core.dto.CreateEmailInput;
import github.AlissonMartin.emailService.core.dto.SendEmailInput;
import github.AlissonMartin.emailService.core.gateway.EmailProviderGateway;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SendEmailCaseImpl implements SendEmailCase {
  private final List<EmailProviderGateway> emailProviders;
  private final CreateEmailCase createEmailCase;
  private AtomicInteger counter = new AtomicInteger(0);

  public SendEmailCaseImpl(List<EmailProviderGateway> emailProviders, CreateEmailCase createEmailCase) {
    this.createEmailCase = createEmailCase;
    this.emailProviders = emailProviders;
  }

  @Override
  public void execute(SendEmailInput input) {
    int index = counter.getAndIncrement() % emailProviders.size();
    EmailProviderGateway emailProvider = emailProviders.get(index);
    try {
      emailProvider.sendEmail(input.to(), input.subject(), input.body());
      createEmailCase.execute(new CreateEmailInput(input.to(), input.subject(), input.body()));
    } catch (Exception e) {
      fallback(input, index);
    }
  }

  private void fallback(SendEmailInput input, int failedIndex) {
    for (int i = 0; i < emailProviders.size(); i++) {
      if (i == failedIndex) continue;

      EmailProviderGateway emailProvider = emailProviders.get(i);
      emailProvider.sendEmail(input.to(), input.subject(), input.body());
      return;
    }
    System.out.println("Could not send the email.");
  }
}
