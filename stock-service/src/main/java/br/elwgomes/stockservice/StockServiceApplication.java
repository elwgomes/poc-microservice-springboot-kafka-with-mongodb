package br.elwgomes.stockservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.elwgomes.base.domain.Order;
import br.elwgomes.stockservice.service.OrderManagerService;

@SpringBootApplication
@EnableKafka
public class StockServiceApplication {

  private static final Logger LOG = LoggerFactory.getLogger(StockServiceApplication.class);

  @Value("${kafka.topics.orders}")
  private String orderTopic;

  @Value("${kafka.topics.stock}")
  private String stockTopic;

  @Value("${kafka.topics.payment}")
  private String paymentTopic;

  public static void main(String[] args) {
    SpringApplication.run(StockServiceApplication.class, args);
    LOG.info("Stock Service is running on: {}");
  }

  @Autowired
  private OrderManagerService service;

  @KafkaListener(topics = "${kafka.topics.orders}", groupId = "${kafka.consumers.stock.group-id}")
  public void orderConsumer(String message) {
    ObjectMapper objMapper = new ObjectMapper();
    objMapper.registerModule(new JavaTimeModule());
    try {
      Order order = objMapper.readValue(message, Order.class);
      LOG.info("Deserialized Order: {}");
      service.process(order);
    } catch (JsonProcessingException e) {
      LOG.error("Error deserializing message: {}", message, e);
    }
    LOG.info("Consumed: {}");
  }

}
