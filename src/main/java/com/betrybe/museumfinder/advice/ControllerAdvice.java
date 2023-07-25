package com.betrybe.museumfinder.advice;

import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Advice class for controller.
 */
@RestControllerAdvice
public class ControllerAdvice {

  @ExceptionHandler(
      InvalidCoordinateException.class
  )
  public ResponseEntity<String> handleInvalidCoordinate(InvalidCoordinateException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body("Coordenada inválida!");
  }

  @ExceptionHandler(
      MuseumNotFoundException.class
  )
  public ResponseEntity<String> handleMuseumNotFound(MuseumNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("Museu não encontrado!");
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleAny(Exception e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Erro interno!");
  }
}
