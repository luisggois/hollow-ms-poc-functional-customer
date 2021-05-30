package com.hollow.microservices.customer.nosql.mapper;

import com.hollow.microservices.customer.core.domain.Customer;
import com.hollow.microservices.customer.nosql.document.CustomerMongoDocument;
import com.hollow.microservices.recurrent.basic.mapper.CentralConfig;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = CentralConfig.class)
public interface CustomerNoSqlMapper {

    Customer toCustomer(CustomerMongoDocument customerDocument);

    List<Customer> toCustomerList(List<CustomerMongoDocument> customerDocumentList);

    CustomerMongoDocument toCustomerDocument(Customer customer);
}
