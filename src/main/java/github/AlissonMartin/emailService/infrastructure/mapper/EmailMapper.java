package github.AlissonMartin.emailService.infrastructure.mapper;

import github.AlissonMartin.emailService.core.dto.SendEmailInput;
import github.AlissonMartin.emailService.core.entities.Email;
import github.AlissonMartin.emailService.infrastructure.dto.email.SendEmailRequest;
import github.AlissonMartin.emailService.infrastructure.persistence.email.EmailEntity;

public class EmailMapper {
  public Email toDomainObj(EmailEntity emailEntity) {
    return new Email(emailEntity.getId(), emailEntity.getAddress(), emailEntity.getSubject(), emailEntity.getBody());
  }

  public SendEmailInput toInput(SendEmailRequest sendEmailRequest) {
    return new SendEmailInput(sendEmailRequest.to(), sendEmailRequest.subject(), sendEmailRequest.body());
  }
}
