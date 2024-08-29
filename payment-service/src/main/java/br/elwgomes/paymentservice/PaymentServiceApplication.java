package br.elwgomes.paymentservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentServiceApplication {

  private final static Logger LOG = LoggerFactory.getLogger(PaymentServiceApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(PaymentServiceApplication.class, args);
    LOG.info("Payment Service is running on: {}");
  }
}
