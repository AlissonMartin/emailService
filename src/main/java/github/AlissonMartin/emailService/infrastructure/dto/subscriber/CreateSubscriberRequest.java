package github.AlissonMartin.emailService.infrastructure.dto.subscriber;

public record CreateSubscriberRequest(String email, Long topic_id) {
}
