package com.betrybe.museumfinder.advice;

import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * Advice class for controller.
 */
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

  @ExceptionHandler({
      InvalidCoordinateException.class
  })
  public ResponseEntity<String> handleInvalidCoordinate(Exception e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("Coordenada inválida!");
  }
}
