package br.elwgomes.orderservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.elwgomes.orderservice.domain.Stock;

public interface StockMongoRepository extends MongoRepository<Stock, String> {
}
