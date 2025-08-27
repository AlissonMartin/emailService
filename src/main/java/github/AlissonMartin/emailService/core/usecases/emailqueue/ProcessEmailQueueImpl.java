package github.AlissonMartin.emailService.core.usecases.emailqueue;

import github.AlissonMartin.emailService.core.dto.SendEmailInput;
import github.AlissonMartin.emailService.core.entities.EmailQueue;
import github.AlissonMartin.emailService.core.entities.Topic;
import github.AlissonMartin.emailService.core.gateway.EmailQueueGateway;
import github.AlissonMartin.emailService.core.usecases.email.SendEmailCase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProcessEmailQueueImpl implements ProcessEmailQueueCase{

  private final EmailQueueGateway emailQueueGateway;
  private final SendEmailCase sendEmailCase;

  public ProcessEmailQueueImpl(EmailQueueGateway emailQueueGateway, SendEmailCase sendEmailCase) {
    this.emailQueueGateway = emailQueueGateway;
    this.sendEmailCase = sendEmailCase;
  }

  @Override
  public void execute() {
    List<EmailQueue> pendingEmails = emailQueueGateway.getLastUnsentEmails();
    List<EmailQueue> lastSentEmailsByTopic = emailQueueGateway.getLastSentEmailsByTopic();
    LocalDateTime now = LocalDateTime.now();

    Map<Long, EmailQueue> lastSentMap = lastSentEmailsByTopic.stream().collect(Collectors.toMap(e -> e.topic().id(), e -> e));

    for (EmailQueue pendingEmail : pendingEmails) {
      Topic topic = pendingEmail.topic();

      SendEmailInput sendEmailInput = new SendEmailInput(pendingEmail.email().address(), pendingEmail.email().subject(), pendingEmail.email().body());

      EmailQueue lastSent = lastSentMap.get(topic.id());

      if (lastSent == null || lastSent.sentAt() == null) {
        sendEmailCase.execute(sendEmailInput);
        continue;
      }

      LocalDateTime nextAvailable = lastSent.sentAt().plus(topic.intervalValue(), topic.intervalUnit().toChronoUnit());

      if (now.isAfter(nextAvailable)) sendEmailCase.execute(sendEmailInput);
    }
  }
}
