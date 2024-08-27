package br.elwgomes.orderservice.domain;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.elwgomes.orderservice.domain.enums.OrderStatus;

@Document(collection = "orders")
public class Order {

  @Id
  private Long id;
  private Set<Product> products;
  private OrderStatus status;

  public Order() {

  }

  public Order(Long id, Set<Product> products, OrderStatus status) {
    this.id = id;
    this.products = products;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public Set<Product> getProducts() {
    return products;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setProducts(Set<Product> products) {
    this.products = products;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

}
