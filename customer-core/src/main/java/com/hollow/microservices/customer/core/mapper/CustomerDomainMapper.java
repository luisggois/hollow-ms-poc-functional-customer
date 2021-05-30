package com.hollow.microservices.customer.core.mapper;


import com.hollow.microservices.customer.core.api.command.CreateCustomerCommand;
import com.hollow.microservices.customer.core.domain.Customer;
import com.hollow.microservices.recurrent.basic.mapper.CentralConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(config = CentralConfig.class, imports = UUID.class)
public interface CustomerDomainMapper {

    @Mapping(target = "id", expression = "java(UUID.randomUUID().toString())")
    Customer toCustomer(CreateCustomerCommand createCustomerCommand);
}
