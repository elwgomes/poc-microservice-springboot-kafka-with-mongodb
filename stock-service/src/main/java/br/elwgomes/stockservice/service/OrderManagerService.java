
package br.elwgomes.stockservice.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.elwgomes.base.domain.Order;
import br.com.elwgomes.base.domain.Product;
import br.com.elwgomes.base.domain.Stock;
import br.com.elwgomes.base.domain.enums.OrderStatus;
import br.com.elwgomes.base.domain.enums.StockDisponibility;
import br.elwgomes.stockservice.repository.OrderMongoRepository;
import br.elwgomes.stockservice.repository.StockMongoRepository;

@Service
public class OrderManagerService {
  private static final Logger LOG = LoggerFactory.getLogger(OrderManagerService.class);

  private final OrderMongoRepository orderRepository;
  private final StockMongoRepository stockRepository;

  public OrderManagerService(OrderMongoRepository orderRepository, StockMongoRepository stockRepository) {
    this.orderRepository = orderRepository;
    this.stockRepository = stockRepository;
  }

  public void process(Order order) {
    Set<Product> unavailableProducts = order.getItems().stream()
        .filter(product -> {
          Optional<Stock> stockOptional = stockRepository.findByProductId(product.getId());
          if (stockOptional.isEmpty()) {
            LOG.warn("Stock not found for product: {}", product.getId());
            return true;
          }
          Stock stock = stockOptional.get();
          LOG.info("Processing product: {} with stock availability: {}", product.getId(), stock.getDisponibility());
          return stock.getDisponibility() == StockDisponibility.UNAVAILABLE;
        })
        .collect(Collectors.toSet());

    if (!unavailableProducts.isEmpty()) {
      cancelOrder(order);
      return;
    }

    packOrder(order);
  }

  private void cancelOrder(Order order) {
    updateOrderStatus(order, OrderStatus.CANCELED);
    LOG.info("Order status updated to CANCELED for order: {}", order.getId());
  }

  private void packOrder(Order order) {
    updateOrderStatus(order, OrderStatus.PACKING);
    LOG.info("Order status updated to PACKING for order: {}", order.getId());
  }

  private void updateOrderStatus(Order order, OrderStatus status) {
    order.setStatus(status);
    orderRepository.save(order);
  }
}
