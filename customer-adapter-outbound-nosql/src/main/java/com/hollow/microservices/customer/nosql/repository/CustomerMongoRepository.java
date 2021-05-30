package com.hollow.microservices.customer.nosql.repository;


import com.hollow.microservices.customer.core.domain.Customer;
import com.hollow.microservices.customer.nosql.document.CustomerMongoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerMongoRepository extends MongoRepository<CustomerMongoDocument, String> {
}
