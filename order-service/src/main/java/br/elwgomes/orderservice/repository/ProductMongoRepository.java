package br.elwgomes.orderservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.elwgomes.orderservice.domain.Product;

@Repository
public interface ProductMongoRepository extends MongoRepository<Product, String> {
}
