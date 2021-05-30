package com.hollow.microservices.customer.nosql.repository;

import com.hollow.microservices.customer.core.domain.Customer;
import com.hollow.microservices.customer.nosql.document.CustomerMongoDocument;
import com.hollow.microservices.recurrent.basic.pagination.persistence.PageFetcher;
import org.springframework.stereotype.Component;

@Component
public class CustomerNoSqlPageFetcher extends PageFetcher<Customer, CustomerMongoDocument, String, CustomerMongoRepository>{
}
