package br.elwgomes.paymentservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.elwgomes.base.domain.Order;

@Repository
public interface OrderMongoRepository extends MongoRepository<Order, String> {
}
