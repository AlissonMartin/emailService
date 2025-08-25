package github.AlissonMartin.emailService.infrastructure.presentation;

import github.AlissonMartin.emailService.core.exception.SubscriberAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(SubscriberAlreadyExistsException.class)
  public ResponseEntity<Map<String, String>> handleSubscriberAlreadyExistsException(SubscriberAlreadyExistsException e) {
    Map<String, String> error = new HashMap<>();
    error.put("message", e.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }
}
