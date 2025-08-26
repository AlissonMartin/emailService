package github.AlissonMartin.emailService.core.dto;

public record CreateEmailQueueEmailInput(CreateEmailInput createEmailInput, Long topic_id) {
}
