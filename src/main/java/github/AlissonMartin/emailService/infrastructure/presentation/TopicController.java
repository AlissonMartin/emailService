package github.AlissonMartin.emailService.infrastructure.presentation;

import github.AlissonMartin.emailService.core.dto.CreateTopicOutput;
import github.AlissonMartin.emailService.core.usecases.emailqueue.CreateEmailQueueEmailCase;
import github.AlissonMartin.emailService.core.usecases.topic.CreateTopicCase;
import github.AlissonMartin.emailService.infrastructure.dto.emailqueue.CreateEmailQueueEmailRequest;
import github.AlissonMartin.emailService.infrastructure.dto.topic.CreateTopicRequest;
import github.AlissonMartin.emailService.infrastructure.dto.topic.CreateTopicResponse;
import github.AlissonMartin.emailService.infrastructure.mapper.TopicMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topics")
public class TopicController {
  private CreateTopicCase createTopicCase;
  private TopicMapper topicMapper;
  private CreateEmailQueueEmailCase createEmailQueueEmailCase;

  public TopicController(CreateTopicCase createTopicCase, CreateEmailQueueEmailCase createEmailQueueEmailCase, TopicMapper topicMapper) {
    this.createTopicCase = createTopicCase;
    this.createEmailQueueEmailCase = createEmailQueueEmailCase;
    this.topicMapper = topicMapper;
  }

  @PostMapping
  ResponseEntity<CreateTopicResponse> create(@RequestBody CreateTopicRequest body) {
    CreateTopicOutput createTopicOutput = createTopicCase.execute(topicMapper.toInput(body));

    return ResponseEntity.status(201).body(topicMapper.toResponse(createTopicOutput));
  }

  @PostMapping("/{topic_id}/email_queue")
  ResponseEntity<Void> createEmailQueueEmail(@PathVariable("topic_id") Long topic_id, @RequestBody CreateEmailQueueEmailRequest body) {
    createEmailQueueEmailCase.execute(topicMapper.toCreateEmailQueueInput(body, topic_id));
    return ResponseEntity.status(201).build();
  }
}
