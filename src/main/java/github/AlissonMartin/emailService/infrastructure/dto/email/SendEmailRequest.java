package github.AlissonMartin.emailService.infrastructure.dto.email;

public record SendEmailRequest(String to, String subject, String body) {
}
