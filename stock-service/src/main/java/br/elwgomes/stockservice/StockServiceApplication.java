package br.elwgomes.stockservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

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

  @KafkaListener(topics = "${kafka.topics.orders}", groupId = "${kafka.consumers.stock.group-id}")
  public void orderConsumer(String message) {
    LOG.info("Consumed: {}" + message);
  }

}
