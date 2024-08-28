package br.elwgomes.orderservice.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.elwgomes.orderservice.controller.response.ErrorHttpResponse;
import br.elwgomes.orderservice.domain.exceptions.InvalidParameterException;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(InvalidParameterException.class)
  public ResponseEntity<ErrorHttpResponse> handleInvalidParameterException(InvalidParameterException ex) {
    ErrorHttpResponse errorResponse = new ErrorHttpResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}
