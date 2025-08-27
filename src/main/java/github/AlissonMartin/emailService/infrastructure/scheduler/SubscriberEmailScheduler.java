package github.AlissonMartin.emailService.infrastructure.scheduler;

import github.AlissonMartin.emailService.core.usecases.emailqueue.ProcessEmailQueueCase;
import org.springframework.scheduling.annotation.Scheduled;

public class SubscriberEmailScheduler {
  private final ProcessEmailQueueCase processEmailQueueCase;

  public SubscriberEmailScheduler(ProcessEmailQueueCase processEmailQueueCase) {
    this.processEmailQueueCase = processEmailQueueCase;
  }

  @Scheduled(fixedRate = 3600000)
  public void sendSubscriberEmails() {
    processEmailQueueCase.execute();
  }
}
