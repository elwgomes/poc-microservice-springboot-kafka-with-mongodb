package br.elwgomes.orderservice.domain;

import java.time.Instant;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.elwgomes.orderservice.domain.enums.OrderStatus;

@Document(collection = "orders")
public class Order {

  @Id
  private String id;
  private Instant moment;
  private OrderStatus status;
  private Set<Product> items;

  public Order() {

  }

  public Order(String id, Instant moment, OrderStatus status, Set<Product> items) {
    this.id = id;
    this.moment = moment;
    this.status = status;
    this.items = items;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Order other = (Order) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Instant getMoment() {
    return moment;
  }

  public void setMoment(Instant moment) {
    this.moment = moment;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public Set<Product> getItems() {
    return items;
  }

  public void setItems(Set<Product> items) {
    this.items = items;
  }

}
