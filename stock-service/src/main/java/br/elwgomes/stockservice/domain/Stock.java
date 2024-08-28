package br.elwgomes.stockservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.elwgomes.stockservice.domain.enums.StockDisponibility;

@Document(collection = "stock")
public class Stock {

  @Id
  private String id;
  private StockDisponibility disponibility;

  public Stock() {
  }

  public Stock(String id, StockDisponibility disponibility) {
    this.id = id;
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

}
