package br.elwgomes.orderservice.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.elwgomes.orderservice.domain.Order;
import br.elwgomes.orderservice.domain.Product;
import br.elwgomes.orderservice.domain.enums.OrderStatus;
import br.elwgomes.orderservice.repository.OrderMongoRepository;
import br.elwgomes.orderservice.repository.ProductMongoRepository;

@Service
@Primary
public class OrderFactoryService {

  @Value("${kafka.topics.orders}")
  private String orderTopic;

  protected Random RANDOM = new Random();
  private static final Logger LOG = LoggerFactory.getLogger(OrderFactoryService.class);
  private final OrderMongoRepository orderRepository;
  private final ProductMongoRepository productRepository;
  private final KafkaTemplate<String, Order> kafkaTemplate;

  public OrderFactoryService(OrderMongoRepository orderRepository, ProductMongoRepository productRepository,
      KafkaTemplate<String, Order> kafkaTemplate) {
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
    this.kafkaTemplate = kafkaTemplate;
  }

  @Async
  @Transactional
  public void execute(Integer quantity) {
    generateOrders(quantity);
  }

  protected void generateOrders(Integer quantity) {
    for (int i = 0; i < quantity; i++) {
      Order order = new Order(randomId(), randomInstant(), randomStatus(), generateItems(RANDOM.nextInt(3) + 1));
      orderRepository.save(order);
      boolean needPayment = (order.getStatus() == OrderStatus.PENDING);
      if (needPayment) {
        kafkaTemplate.send(orderTopic, order.getId(), order);
        LOG.info("Sent: { }");
      }
    }
  }

  protected Set<Product> generateItems(int quantityProducts) {
    Set<Product> items = new HashSet<>();
    for (int j = 0; j < quantityProducts; j++) {
      Product product = new Product(randomId(), RANDOM.nextInt(3) + 1);
      items.add(product);
      productRepository.save(product);
    }
    return items;
  }

  protected String randomId() {
    return UUID.randomUUID().toString();
  }

  protected OrderStatus randomStatus() {
    OrderStatus[] statuses = OrderStatus.values();
    return statuses[RANDOM.nextInt(statuses.length)];
  }

  protected Instant randomInstant() {
    return Instant.now().minus(RANDOM.nextInt(30), ChronoUnit.DAYS)
        .minus(RANDOM.nextInt(24), ChronoUnit.HOURS)
        .minus(RANDOM.nextInt(60), ChronoUnit.MINUTES);
  }

}
