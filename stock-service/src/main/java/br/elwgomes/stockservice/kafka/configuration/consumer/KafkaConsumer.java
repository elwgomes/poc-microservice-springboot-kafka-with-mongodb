package br.elwgomes.stockservice.kafka.configuration.consumer;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.elwgomes.base.domain.Order;
import br.elwgomes.stockservice.service.OrderManagerService;

@Configuration
@EnableKafka
public class KafkaConsumer {

  private final static Logger LOG = LoggerFactory.getLogger(KafkaConsumer.class);

  @Value("${kafka.topics.stock}")
  private String stockTopic;

  @Bean
  public NewTopic stockTopic() {
    return TopicBuilder
        .name(stockTopic)
        .partitions(1)
        .compact()
        .build();
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
