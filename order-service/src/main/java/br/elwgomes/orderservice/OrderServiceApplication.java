package br.elwgomes.orderservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderServiceApplication {

  private static final Logger LOG = LoggerFactory.getLogger(OrderServiceApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(OrderServiceApplication.class, args);
    LOG.info("Order Service is running on: {}");
  }

}
