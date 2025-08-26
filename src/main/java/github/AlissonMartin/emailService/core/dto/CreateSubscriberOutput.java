package github.AlissonMartin.emailService.core.dto;

import github.AlissonMartin.emailService.core.entities.Topic;

public record CreateSubscriberOutput(Long id, String email, Topic topic) {
}
