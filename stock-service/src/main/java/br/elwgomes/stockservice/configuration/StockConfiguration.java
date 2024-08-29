package br.elwgomes.stockservice.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockConfiguration {

  private static final Logger LOG = LoggerFactory.getLogger(StockConfiguration.class);

  @Bean
  CommandLineRunner commandLineRunner() {
    return args -> {
      LOG.info("COMMAND LINE RUNNER: {}");
    };
  }

}
