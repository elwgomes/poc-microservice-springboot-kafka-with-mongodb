package br.elwgomes.orderservice.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderHttpResponse {
  private HttpStatus status;
  private String message;
}
