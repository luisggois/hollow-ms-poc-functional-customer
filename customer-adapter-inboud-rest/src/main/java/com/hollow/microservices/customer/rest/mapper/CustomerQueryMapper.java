package com.hollow.microservices.customer.rest.mapper;

import com.hollow.microservices.customer.core.domain.Customer;
import com.hollow.microservices.customer.rest.dto.CustomerDto;
import com.hollow.microservices.recurrent.basic.mapper.CentralConfig;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = CentralConfig.class)
public interface CustomerQueryMapper {

    CustomerDto toCustomerDto(Customer customer);

    List<CustomerDto> toCustomerDtoList(List<Customer> customerList);
}
