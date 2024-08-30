package br.elwgomes.stockservice.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.elwgomes.base.domain.Order;
import br.com.elwgomes.base.domain.Product;
import br.com.elwgomes.base.domain.enums.OrderStatus;
import br.com.elwgomes.base.domain.enums.StockDisponibility;
import br.elwgomes.stockservice.repository.OrderMongoRepository;
import br.elwgomes.stockservice.repository.ProductMongoRepository;
import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class OrderManagerService {
  private static final Logger LOG = LoggerFactory.getLogger(OrderManagerService.class);

  @Value("${kafka.topics.stock}")
  private String stockTopic;

  private final OrderMongoRepository orderRepository;
  private final ProductMongoRepository productRepository;
  private final KafkaTemplate<String, Order> kafkaTemplate;

  @Async
  @Transactional
  public void process(Order order) {
    Set<Product> unavailableProducts = order.getItems().stream()
        .filter(product -> {
          Optional<Product> optionalProduct = productRepository.findById(product.getId());
          if (optionalProduct.isEmpty()) {
            LOG.warn("Product not found for product ID: {}", product.getId());
            return true;
          }
          Product p = optionalProduct.get();
          LOG.info("Processing product: {} with stock availability: {}", p.getId(), p.getDisponibility());
          return p.getDisponibility() == StockDisponibility.UNAVAILABLE;
        })
        .collect(Collectors.toSet());

    if (!unavailableProducts.isEmpty()) {
      cancelOrder(order);
      return;
    }

    packOrder(order);
    kafkaTemplate.send(stockTopic, order.getId(), order);
  }

  protected void cancelOrder(Order order) {
    updateOrderStatus(order, OrderStatus.CANCELED);
    LOG.info("Order status updated to CANCELED for order: {}", order.getId());
  }

  protected void packOrder(Order order) {
    updateOrderStatus(order, OrderStatus.PACKING);
    LOG.info("Order status updated to PACKING for order: {}", order.getId());
  }

  protected void updateOrderStatus(Order order, OrderStatus status) {
    order.setStatus(status);
    orderRepository.save(order);
  }
}
