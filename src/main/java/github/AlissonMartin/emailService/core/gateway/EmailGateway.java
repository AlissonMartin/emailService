package github.AlissonMartin.emailService.core.gateway;

import github.AlissonMartin.emailService.core.entities.Email;

public interface EmailGateway {
  Email createEmail(String address, String subject, String body);
}
