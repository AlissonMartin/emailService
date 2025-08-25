package github.AlissonMartin.emailService.infrastructure.gateway;


import github.AlissonMartin.emailService.core.gateway.EmailProviderGateway;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class AwsSesGateway implements EmailProviderGateway {

  private final String host;
  private final int port;
  private final String username;
  private final String password;

  public AwsSesGateway(String host, int port, String username, String password) {
    this.host = host;
    this.port = port;
    this.username = username;
    this.password = password;
  }

  @Override
  public void sendEmail(String address, String subject, String body) {
    try {
      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", String.valueOf(port));

      Session session = Session.getInstance(props,
              new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                  return new PasswordAuthentication(username, password);
                }
              });

      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(""));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
      message.setSubject(subject);
      message.setText(body);

      Transport.send(message);
      System.out.println("Enviado via AWS SES para " + address);
    } catch (Exception e) {
      throw new RuntimeException("Erro ao enviar email via AWS SES", e);
    }
  }
}
