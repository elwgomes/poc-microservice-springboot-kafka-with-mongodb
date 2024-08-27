package br.elwgomes.orderservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {

  @Id
  private Long id;
  private Integer quantity;
}
