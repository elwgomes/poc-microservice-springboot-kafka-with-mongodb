package br.elwgomes.orderservice.domain.exceptions;

public class InvalidParameterException extends RuntimeException {
  public InvalidParameterException(String message) {
    super(message);
  }
}
