package com.sc.sebokbence.scaddressservice.advice;

import com.sc.sebokbence.scaddressservice.exception.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ErrorAdvice {
  @ExceptionHandler(NotFoundException.class)
  ResponseEntity<?> handleNotFoundException(NotFoundException ex) {
    return ResponseEntity.notFound().build();
  }
}
