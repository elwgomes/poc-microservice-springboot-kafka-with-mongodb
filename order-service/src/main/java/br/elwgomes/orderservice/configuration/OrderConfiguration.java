package br.elwgomes.orderservice.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfiguration {

  private static final Logger LOG = LoggerFactory.getLogger(OrderConfiguration.class);

  CommandLineRunner commandLineRunner() {
    return args -> {
      LOG.info("Command Line Runner");
    };
  }
}
