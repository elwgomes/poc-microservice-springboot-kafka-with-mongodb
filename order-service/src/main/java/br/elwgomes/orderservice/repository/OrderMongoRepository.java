package br.elwgomes.orderservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.elwgomes.orderservice.domain.Order;

public interface OrderMongoRepository extends MongoRepository<Order, Long> {
}
