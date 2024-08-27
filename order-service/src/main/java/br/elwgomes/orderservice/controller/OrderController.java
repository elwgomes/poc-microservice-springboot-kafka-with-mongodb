package br.elwgomes.orderservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.elwgomes.orderservice.domain.Order;
import br.elwgomes.orderservice.repository.OrderMongoRepository;
import br.elwgomes.orderservice.service.OrderGenerator;

@RestController
@RequestMapping("orders")
public class OrderController {

  private final OrderMongoRepository repository;
  private final OrderGenerator generator;

  public OrderController(OrderMongoRepository repository, OrderGenerator generator) {
    this.repository = repository;
    this.generator = generator;
  }

  @PostMapping
  public Order create(@RequestBody Order order) {
    return order;
  }

  @PostMapping("generate")
  public void generate() {
    generator.generate();
  }

  @GetMapping
  public List<Order> all() {
    return repository.findAll();
  }

}
