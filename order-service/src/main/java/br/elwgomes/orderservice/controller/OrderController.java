package br.elwgomes.orderservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.elwgomes.orderservice.controller.response.OrderHttpResponse;
import br.elwgomes.orderservice.domain.Order;
import br.elwgomes.orderservice.domain.exceptions.InvalidParameterException;
import br.elwgomes.orderservice.repository.OrderMongoRepository;
import br.elwgomes.orderservice.service.OrderFactoryService;

@RestController
@RequestMapping("orders")
public class OrderController {

  private final OrderMongoRepository repository;
  private final OrderFactoryService factory;

  public OrderController(OrderMongoRepository repository, OrderFactoryService factory) {
    this.repository = repository;
    this.factory = factory;
  }

  @PostMapping
  public Order create(@RequestBody Order order) {
    return order;
  }

  @PostMapping("generate")
  public ResponseEntity<OrderHttpResponse> generateOrders(@RequestParam Integer quantity) {
    boolean isQuantityValid = (quantity > 0);
    if (!isQuantityValid) {
      throw new InvalidParameterException("You must generate one or more orders.");
    }
    factory.execute(quantity);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new OrderHttpResponse(HttpStatus.CREATED, quantity + " orders has been generated."));
  }

  @GetMapping
  public List<Order> all() {
    return repository.findAll();
  }

}
