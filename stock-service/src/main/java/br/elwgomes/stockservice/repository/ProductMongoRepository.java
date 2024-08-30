package br.elwgomes.stockservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.elwgomes.base.domain.Product;

@Repository
public interface ProductMongoRepository extends MongoRepository<Product, String> {
}
