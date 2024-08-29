package br.com.elwgomes.base.domain.exceptions;

public class InvalidParameterException extends RuntimeException {
  public InvalidParameterException(String message) {
    super(message);
  }
}
