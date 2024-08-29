package br.elwgomes.orderservice.repository;

import br.com.elwgomes.base.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMongoRepository extends MongoRepository<Product, String> {
}
