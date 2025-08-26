package github.AlissonMartin.emailService.infrastructure.presentation;

import github.AlissonMartin.emailService.core.exception.EntityNotFoundException;
import github.AlissonMartin.emailService.core.exception.EntityAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EntityAlreadyExistsException.class)
  public ResponseEntity<Map<String, String>> handleSubscriberAlreadyExistsException(EntityAlreadyExistsException e) {
    Map<String, String> error = new HashMap<>();
    error.put("message", e.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleEntityNotFoundException(EntityNotFoundException e) {
    Map<String, String> error = new HashMap<>();
    error.put("message", e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }
}
