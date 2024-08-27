package br.elwgomes.orderservice.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.elwgomes.orderservice.domain.Order;
import br.elwgomes.orderservice.domain.Product;
import br.elwgomes.orderservice.domain.enums.OrderStatus;
import br.elwgomes.orderservice.repository.OrderMongoRepository;

@Service
public class OrderGenerator {

  protected Random RANDOM = new Random();
  private final OrderMongoRepository repository;
  private final KafkaTemplate<String, Order> kafka;

  public OrderGenerator(OrderMongoRepository repository, KafkaTemplate<String, Order> kafka) {
    this.repository = repository;
    this.kafka = kafka;
  }

  @Async
  public void generate() {
    for (int i = 0; i < 100; i++) {
      Set<Product> items = new HashSet<>();
      int quantityProducts = RANDOM.nextInt(3) + 1;
      for (int j = 0; j < quantityProducts; j++) {
        Product product = new Product(randomId(), RANDOM.nextInt(3) + 1);
        items.add(product);
      }
      Order order = new Order(randomId(), randomInstant(), randomStatus(), items);
      repository.save(order);
    }
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
