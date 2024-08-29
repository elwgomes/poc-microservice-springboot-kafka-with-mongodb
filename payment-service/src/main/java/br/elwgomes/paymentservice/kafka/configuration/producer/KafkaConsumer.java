package br.elwgomes.paymentservice.kafka.configuration.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@EnableKafka
public class KafkaConsumer {

  private final static Logger LOG = LoggerFactory.getLogger(KafkaConsumer.class);

  @KafkaListener(topics = "${kafka.topics.payment}", groupId = "${kafka.consumers.payment.group-id}")
  public void consumer(String message) {
    LOG.info("Consumed: {} ");
  }

}
