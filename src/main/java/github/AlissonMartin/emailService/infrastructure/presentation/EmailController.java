package github.AlissonMartin.emailService.infrastructure.presentation;

import github.AlissonMartin.emailService.core.usecases.email.SendEmailCase;
import github.AlissonMartin.emailService.infrastructure.dto.email.SendEmailRequest;
import github.AlissonMartin.emailService.infrastructure.mapper.EmailMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {
  private SendEmailCase sendEmailCase;
  private EmailMapper emailMapper;

  public EmailController(SendEmailCase sendEmailCase, EmailMapper emailMapper) {
    this.sendEmailCase = sendEmailCase;
    this.emailMapper = emailMapper;
  }

  @PostMapping("/send")
  public ResponseEntity sendEmail(@RequestBody SendEmailRequest body) {
    sendEmailCase.execute(emailMapper.toInput(body));
    return ResponseEntity.status(201).build();
  }
}
