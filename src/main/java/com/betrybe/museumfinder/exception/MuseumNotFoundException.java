package com.betrybe.museumfinder.exception;

/**
 * Classe de exceção.
 */
public class MuseumNotFoundException extends RuntimeException {

  /**
   * Seu constructor.
   */
  public MuseumNotFoundException(String message) {
    super(message);
  }
}
