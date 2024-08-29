package br.elwgomes.stockservice.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.elwgomes.base.domain.Order;
import br.com.elwgomes.base.domain.Product;
import br.com.elwgomes.base.domain.Stock;
import br.com.elwgomes.base.domain.enums.OrderStatus;
import br.com.elwgomes.base.domain.enums.StockDisponibility;
import br.elwgomes.stockservice.repository.OrderMongoRepository;
import br.elwgomes.stockservice.repository.StockMongoRepository;

@Service
@Primary
public class OrderManagerService {
  private static final Logger LOG = LoggerFactory.getLogger(OrderManagerService.class);

  private final OrderMongoRepository orderRepository;
  private final StockMongoRepository stockRepository;

  public OrderManagerService(OrderMongoRepository orderRepository, StockMongoRepository stockRepository) {
    this.orderRepository = orderRepository;
    this.stockRepository = stockRepository;
  }

  public void process(Order order) {
    try {
      for (Product product : order.getItems()) {
        Optional<Stock> stockOptional = stockRepository.findByProductId(product.getId());
        if (stockOptional.isEmpty()) {
          LOG.warn("Stock not found for product: {}", product.getId());
          continue;
        }
        Stock stock = stockOptional.get();
        LOG.info("Processing product: {} with stock availability: {}", product.getId(), stock.getDisponibility());
        if (stock.getDisponibility() == StockDisponibility.UNAVAILABLE) {
          order.setStatus(OrderStatus.CANCELED);
          orderRepository.save(order);
          LOG.info("Order status updated to CANCELED for order: {}", order.getId());
        }
        order.setStatus(OrderStatus.PACKING);
        orderRepository.save(order);
        LOG.info("Order status updated to PACKING for order: {}", order.getId());
      }
    } catch (Exception e) {
      LOG.error("Error in processing order: {}", order.getId(), e);
    }
  }
}
