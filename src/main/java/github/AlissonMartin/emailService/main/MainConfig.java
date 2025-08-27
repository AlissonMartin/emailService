package github.AlissonMartin.emailService.main;

import github.AlissonMartin.emailService.core.gateway.*;
import github.AlissonMartin.emailService.core.usecases.email.CreateEmailCase;
import github.AlissonMartin.emailService.core.usecases.email.CreateEmailCaseImpl;
import github.AlissonMartin.emailService.core.usecases.email.SendEmailCase;
import github.AlissonMartin.emailService.core.usecases.email.SendEmailCaseImpl;
import github.AlissonMartin.emailService.core.usecases.emailqueue.CreateEmailQueueEmailCase;
import github.AlissonMartin.emailService.core.usecases.emailqueue.CreateEmailQueueEmailCaseImpl;
import github.AlissonMartin.emailService.core.usecases.emailqueue.ProcessEmailQueueCase;
import github.AlissonMartin.emailService.core.usecases.emailqueue.ProcessEmailQueueImpl;
import github.AlissonMartin.emailService.core.usecases.subscriber.CreateSubscriberCase;
import github.AlissonMartin.emailService.core.usecases.subscriber.CreateSubscriberCaseImpl;
import github.AlissonMartin.emailService.core.usecases.topic.CreateTopicCase;
import github.AlissonMartin.emailService.core.usecases.topic.CreateTopicCaseImpl;
import github.AlissonMartin.emailService.infrastructure.gateway.*;
import github.AlissonMartin.emailService.infrastructure.mapper.EmailMapper;
import github.AlissonMartin.emailService.infrastructure.mapper.EmailQueueMapper;
import github.AlissonMartin.emailService.infrastructure.mapper.SubscriberMapper;
import github.AlissonMartin.emailService.infrastructure.mapper.TopicMapper;
import github.AlissonMartin.emailService.infrastructure.persistence.email.EmailRepository;
import github.AlissonMartin.emailService.infrastructure.persistence.emailqueue.EmailQueueRepository;
import github.AlissonMartin.emailService.infrastructure.persistence.subscriber.SubscriberRepository;
import github.AlissonMartin.emailService.infrastructure.persistence.topic.TopicRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MainConfig {

  @Bean
  public SendEmailCase sendEmailCase(
          @Value("${email.providers.gmail.host}") String gHost,
          @Value("${email.providers.gmail.port}") int gPort,
          @Value("${email.providers.gmail.username}") String gUser,
          @Value("${email.providers.gmail.password}") String gPass,
          @Value("${email.providers.gmail.from}") String gFrom,
          @Value("${email.providers.ses.host}") String sesHost,
          @Value("${email.providers.ses.port}") int sesPort,
          @Value("${email.providers.ses.username}") String sesUser,
          @Value("${email.providers.ses.password}") String sesPass,
          @Value("${email.providers.ses.from}") String sesFrom,
          CreateEmailCase createEmailCase
  ) {
    EmailProviderGateway gmail = new GmailGateway(gHost, gPort, gUser, gPass, gFrom);
    EmailProviderGateway ses = new AwsSesGateway(sesHost, sesPort, sesUser, sesPass, sesFrom);
    return new SendEmailCaseImpl(List.of(ses, gmail), createEmailCase);
  }

  @Bean
  CreateSubscriberCase createSubscriberCase(SubscriberGateway subscriberGateway, TopicGateway topicGateway) {
    return new CreateSubscriberCaseImpl(subscriberGateway, topicGateway);
  }

  @Bean
  CreateTopicCase createTopicCase(TopicGateway topicGateway) {
    return new CreateTopicCaseImpl(topicGateway);
  }

  @Bean
  CreateEmailCase createEmailCase(EmailGateway emailGateway) {
    return new CreateEmailCaseImpl(emailGateway);
  }

  @Bean
  CreateEmailQueueEmailCase createEmailQueueEmailCase(CreateEmailCase createEmailCase, EmailQueueGateway emailQueueGateway, TopicGateway topicGateway) {
    return new CreateEmailQueueEmailCaseImpl(createEmailCase, emailQueueGateway, topicGateway);
  }

  @Bean
  ProcessEmailQueueCase processEmailQueueCase(EmailQueueGateway emailQueueGateway, SendEmailCase sendEmailCase) {
    return new ProcessEmailQueueImpl(emailQueueGateway, sendEmailCase);
  }

  @Bean
  SubscriberGateway subscriberGateway(SubscriberRepository subscriberRepository, SubscriberMapper subscriberMapper, TopicMapper topicMapper) {
    return new SubscriberRepositoryGateway(subscriberRepository, topicMapper, subscriberMapper);
  }

  @Bean
  TopicGateway topicGateway(TopicRepository topicRepository, TopicMapper topicMapper) {
    return new TopicRepositoryGateway(topicRepository, topicMapper);
  }

  @Bean
  EmailGateway emailGateway(EmailRepository emailRepository, EmailMapper emailMapper) {
    return new EmailRepositoryGateway(emailRepository, emailMapper);
  }

  @Bean
  EmailQueueGateway emailQueueGateway(EmailQueueRepository emailQueueRepository, EmailMapper emailMapper, TopicMapper topicMapper, EmailQueueMapper emailQueueMapper) {
    return new EmailQueueRepositoryGateway(emailQueueRepository, emailMapper, topicMapper, emailQueueMapper);
  }

  @Bean
  EmailMapper emailMapper() {
    return new EmailMapper();
  }

  @Bean
  EmailQueueMapper emailQueueMapper(EmailMapper emailMapper, TopicMapper topicMapper) {
    return new EmailQueueMapper(emailMapper, topicMapper);
  }

  @Bean
  SubscriberMapper subscriberMapper() {
    return new SubscriberMapper();
  }

  @Bean
  TopicMapper topicMapper() {
    return new TopicMapper();
  }
}
