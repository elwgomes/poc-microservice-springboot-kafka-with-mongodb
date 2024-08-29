package br.elwgomes.stockservice.kafka.configuration.producer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import br.com.elwgomes.base.domain.Order;

@Configuration
public class KafkaProducer {

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

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
