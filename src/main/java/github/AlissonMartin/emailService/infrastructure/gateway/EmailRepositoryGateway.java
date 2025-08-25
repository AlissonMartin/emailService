package github.AlissonMartin.emailService.infrastructure.gateway;

import github.AlissonMartin.emailService.core.entities.Email;
import github.AlissonMartin.emailService.core.gateway.EmailGateway;
import github.AlissonMartin.emailService.infrastructure.mapper.EmailMapper;
import github.AlissonMartin.emailService.infrastructure.persistence.email.EmailEntity;
import github.AlissonMartin.emailService.infrastructure.persistence.email.EmailRepository;

public class EmailRepositoryGateway implements EmailGateway {
  private final EmailRepository emailRepository;
  private final EmailMapper emailMapper;

  public EmailRepositoryGateway(EmailRepository emailRepository, EmailMapper emailMapper) {
    this.emailRepository = emailRepository;
    this.emailMapper = emailMapper;
  }

  @Override
  public Email createEmail(String address, String subject, String body) {
    EmailEntity emailEntity = new EmailEntity();
    emailEntity.setAddress(address);
    emailEntity.setSubject(subject);
    emailEntity.setBody(body);

    return emailMapper.toDomainObj(emailRepository.save(emailEntity));
  }
}
