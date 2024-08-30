package br.com.elwgomes.base.domain.enums;

public enum OrderStatus {
  CANCELED(1),
  PENDING(2),
  PACKING(3),
  PAID(4),
  WAITING(5);

  private int code;

  private OrderStatus(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  public static OrderStatus valueOf(int code) {
    for (OrderStatus value : OrderStatus.values()) {
      if (value.getCode() == code) {
        return value;
      }
    }
    throw new IllegalArgumentException("invalid order status code");
  }
}
