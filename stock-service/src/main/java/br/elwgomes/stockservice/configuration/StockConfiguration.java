package br.elwgomes.stockservice.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockConfiguration {

  @Bean
  CommandLineRunner commandLineRunner() {
    return args -> {

    };
  }

}
