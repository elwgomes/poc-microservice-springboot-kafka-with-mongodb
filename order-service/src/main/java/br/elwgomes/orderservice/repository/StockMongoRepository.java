package br.elwgomes.orderservice.repository;

import br.com.elwgomes.base.domain.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMongoRepository extends MongoRepository<Stock, String> {
}
