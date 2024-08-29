package br.com.elwgomes.base.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StockDisponibility {

  UNAVAILABLE(1),
  AVAILABLE(2);

  private int code;

  public static StockDisponibility valueOf(int code) {
    for (StockDisponibility value : StockDisponibility.values()) {
      if (value.getCode() == code) {
        return value;
      }
    }
    throw new IllegalArgumentException("invalid order status code");
  }
}
