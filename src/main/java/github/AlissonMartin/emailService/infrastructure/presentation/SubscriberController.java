package github.AlissonMartin.emailService.infrastructure.presentation;

import github.AlissonMartin.emailService.core.dto.CreateSubscriberInput;
import github.AlissonMartin.emailService.core.dto.CreateSubscriberOutput;
import github.AlissonMartin.emailService.core.usecases.subscriber.CreateSubscriberCase;
import github.AlissonMartin.emailService.infrastructure.dto.subscriber.CreateSubscriberRequest;
import github.AlissonMartin.emailService.infrastructure.dto.subscriber.CreateSubscriberResponse;
import github.AlissonMartin.emailService.infrastructure.mapper.SubscriberMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscribers")
public class SubscriberController {
  private CreateSubscriberCase createSubscriberCase;
  private SubscriberMapper subscriberMapper;

  public SubscriberController(CreateSubscriberCase createSubscriberCase, SubscriberMapper subscriberMapper) {
    this.createSubscriberCase = createSubscriberCase;
    this.subscriberMapper = subscriberMapper;
  }

  @PostMapping
  ResponseEntity<CreateSubscriberResponse> create(@RequestBody CreateSubscriberRequest subscriber) {
    CreateSubscriberOutput createSubscriberOutput = createSubscriberCase.execute(new CreateSubscriberInput(subscriber.email(), subscriber.topic_id()));

    return ResponseEntity.status(201).body(subscriberMapper.toResponseObj(createSubscriberOutput));
  }
}
