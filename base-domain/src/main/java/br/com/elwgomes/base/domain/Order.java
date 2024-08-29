package br.com.elwgomes.base.domain;

import java.time.Instant;
import java.util.Set;

import br.com.elwgomes.base.domain.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  private String id;
  private Instant moment;
  private OrderStatus status;
  private Set<Product> items;
}
