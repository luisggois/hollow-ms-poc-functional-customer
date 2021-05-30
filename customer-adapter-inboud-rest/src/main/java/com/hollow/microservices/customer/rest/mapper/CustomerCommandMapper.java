package com.hollow.microservices.customer.rest.mapper;

import com.hollow.microservices.customer.core.api.command.CreateCustomerCommand;
import com.hollow.microservices.customer.core.domain.Customer;
import com.hollow.microservices.customer.rest.dto.CreateCustomerRequestDto;
import com.hollow.microservices.customer.rest.dto.CustomerDto;
import com.hollow.microservices.recurrent.basic.mapper.CentralConfig;
import org.mapstruct.Mapper;

@Mapper(config = CentralConfig.class)
public interface CustomerCommandMapper {

    CreateCustomerCommand toCreateCustomerCommand(CreateCustomerRequestDto createCustomerRequestDto, String channel);

    CustomerDto toCustomerDto(Customer customer);
}
