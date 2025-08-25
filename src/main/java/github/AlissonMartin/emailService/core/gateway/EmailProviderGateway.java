package github.AlissonMartin.emailService.core.gateway;

public interface EmailProviderGateway {
  void sendEmail(String address, String subject, String body);
}
