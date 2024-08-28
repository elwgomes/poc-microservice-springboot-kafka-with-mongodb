package br.elwgomes.orderservice;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import br.elwgomes.orderservice.domain.Order;

@SpringBootApplication
public class OrderServiceApplication {

  private static final Logger LOG = LoggerFactory.getLogger(OrderServiceApplication.class);

  @Value("${kafka.topics.orders}")
  private String orderTopic;

  @Value("${kafka.topics.stock}")
  private String stockTopic;

  @Value("${kafka.topics.payment}")
  private String paymentTopic;

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  public static void main(String[] args) {
    SpringApplication.run(OrderServiceApplication.class, args);
    LOG.info("Order Service is running on: {}");
  }

  @Bean
  public CommandLineRunner commandLineRunner() {
    return args -> {

    };
  }

  @Bean
  public NewTopic orderTopic() {
    return TopicBuilder
        .name(orderTopic)
        .partitions(1)
        .compact()
        .build();
  }

  @Bean
  public NewTopic stockTopic() {
    return TopicBuilder
        .name(stockTopic)
        .partitions(1)
        .compact()
        .build();
  }

  @Bean
  public NewTopic paymentTopic() {
    return TopicBuilder
        .name(paymentTopic)
        .partitions(1)
        .compact()
        .build();
  }

  @Bean
  public Map<String, Object> kafkaProducerProps() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    return configProps;
  }

  @Bean
  public ProducerFactory<String, Order> kafkaProducerFactory() {
    return new DefaultKafkaProducerFactory<>(kafkaProducerProps());
  }

  @Bean
  public KafkaTemplate<String, Order> kafkaTemplate() {
    return new KafkaTemplate<>(kafkaProducerFactory());
  }

}
