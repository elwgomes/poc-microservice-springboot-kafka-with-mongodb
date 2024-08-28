package br.elwgomes.orderservice.controller.response;

import org.springframework.http.HttpStatus;

public class OrderHttpResponse {
  private HttpStatus status;
  private String message;

  public OrderHttpResponse() {

  }

  public OrderHttpResponse(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
