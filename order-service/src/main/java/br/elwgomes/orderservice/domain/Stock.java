package br.elwgomes.orderservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.elwgomes.orderservice.domain.enums.StockDisponibility;

@Document(collection = "stock")
public class Stock {

  @Id
  private String id;
  private String productId;
  private StockDisponibility disponibility;

  public Stock() {

  }

  public Stock(String id, String productId, StockDisponibility disponibility) {
    this.id = id;
    this.productId = productId;
    this.disponibility = disponibility;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public StockDisponibility getDisponibility() {
    return disponibility;
  }

  public void setDisponibility(StockDisponibility disponibility) {
    this.disponibility = disponibility;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

}
