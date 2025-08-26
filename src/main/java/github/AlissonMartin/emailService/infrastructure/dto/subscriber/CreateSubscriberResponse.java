package github.AlissonMartin.emailService.infrastructure.dto.subscriber;

import github.AlissonMartin.emailService.core.entities.Topic;

public record CreateSubscriberResponse(Long id, String email, Topic topic) {
}
