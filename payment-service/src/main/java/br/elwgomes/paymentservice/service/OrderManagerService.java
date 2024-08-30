package br.elwgomes.paymentservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.elwgomes.base.domain.Order;
import br.com.elwgomes.base.domain.enums.OrderStatus;
import br.elwgomes.paymentservice.repository.OrderMongoRepository;

@Service
@Primary
public class OrderManagerService {

  private static final Logger LOG = LoggerFactory.getLogger(OrderManagerService.class);

  private final OrderMongoRepository orderRepository;

  public OrderManagerService(OrderMongoRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Async
  @Transactional
  public void process(Order order) {
    if (order.getStatus() == OrderStatus.PACKING) {
      paidOrder(order);
      LOG.info("Packed order consumed: {}", order.getId());
    }
  }

  protected void paidOrder(Order order) {
    updateOrderStatus(order, OrderStatus.PAID);
    LOG.info("Order was paid and status updated to PAID for order: {}", order.getId());
  }

  protected void updateOrderStatus(Order order, OrderStatus status) {
    order.setStatus(status);
    orderRepository.save(order);
  }
}
