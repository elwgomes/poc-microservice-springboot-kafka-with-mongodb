package br.elwgomes.orderservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.elwgomes.orderservice.domain.Order;
import br.elwgomes.orderservice.repository.OrderMongoRepository;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

  private final OrderMongoRepository repository;

  public OrderController(OrderMongoRepository repository) {
    this.repository = repository;
  }

  @GetMapping
  public List<Order> all() {
    return repository.findAll();
  }

}
