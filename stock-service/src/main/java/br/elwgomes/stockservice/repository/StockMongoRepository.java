package br.elwgomes.stockservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.elwgomes.base.domain.Stock;

@Repository
public interface StockMongoRepository extends MongoRepository<Stock, String> {
  Optional<Stock> findByProductId(String productId);
}
