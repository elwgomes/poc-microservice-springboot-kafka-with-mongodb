package br.elwgomes.orderservice.controller.response;

import org.springframework.http.HttpStatus;

public class ErrorHttpResponse {
  private HttpStatus status;
  private String error;

  public ErrorHttpResponse() {

  }

  public ErrorHttpResponse(HttpStatus status, String error) {
    this.status = status;
    this.error = error;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

}
