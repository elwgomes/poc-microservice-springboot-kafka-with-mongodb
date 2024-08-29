package br.elwgomes.stockservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockServiceApplication {

  private static final Logger LOG = LoggerFactory.getLogger(StockServiceApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(StockServiceApplication.class, args);
    LOG.info("Stock Service is running on: {}");
  }

}
