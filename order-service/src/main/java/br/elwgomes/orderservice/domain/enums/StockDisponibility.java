package br.elwgomes.orderservice.domain.enums;

public enum StockDisponibility {

  UNAVAILABLE(1),
  AVAILABLE(2);

  private int code;

  private StockDisponibility(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  public static StockDisponibility valueOf(int code) {
    for (StockDisponibility value : StockDisponibility.values()) {
      if (value.getCode() == code) {
        return value;
      }
    }
    throw new IllegalArgumentException("invalid order status code");
  }
}
