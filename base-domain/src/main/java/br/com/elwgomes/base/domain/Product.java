package br.com.elwgomes.base.domain;

import br.com.elwgomes.base.domain.enums.StockDisponibility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
  private String id;
  private Integer quantity;
  private StockDisponibility disponibility;
}
