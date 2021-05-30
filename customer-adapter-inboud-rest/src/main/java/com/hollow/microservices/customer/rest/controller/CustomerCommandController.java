package com.hollow.microservices.customer.rest.controller;

import com.hollow.microservices.customer.core.api.command.CreateCustomerCommand;
import com.hollow.microservices.customer.core.api.command.DeleteCustomerCommand;
import com.hollow.microservices.customer.core.domain.Customer;
import com.hollow.microservices.customer.core.port.in.CustomerOperationsUseCase;
import com.hollow.microservices.customer.rest.api.CustomerCommandApi;
import com.hollow.microservices.customer.rest.dto.CreateCustomerRequestDto;
import com.hollow.microservices.customer.rest.dto.CustomerDto;
import com.hollow.microservices.customer.rest.mapper.CustomerCommandMapper;
import com.hollow.microservices.recurrent.errorhandling.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@Validated
@RequiredArgsConstructor
public class CustomerCommandController implements CustomerCommandApi {

    private final CustomerOperationsUseCase customerOperationsUseCase;
    private final CustomerCommandMapper customerCommandMapper;

    @Override
    public ResponseEntity<CustomerDto> create(CreateCustomerRequestDto createCustomerRequestDto, String channel) {
        CreateCustomerCommand createCustomerCommand = this.customerCommandMapper.toCreateCustomerCommand(createCustomerRequestDto, channel);

        Customer customer  = this.customerOperationsUseCase.create(createCustomerCommand);

        CustomerDto customerDto = this.customerCommandMapper.toCustomerDto(customer);
        URI customerLocation = this.createUri("/{customerId}", customerDto.getId());

        return ResponseEntity.created(customerLocation).body(customerDto);
    }

    @Override
    public ResponseEntity<Void> deleteById(Long customerId) throws BaseException {
        DeleteCustomerCommand deleteCustomerCommand = DeleteCustomerCommand.builder().id(customerId).build();
        this.customerOperationsUseCase.delete(deleteCustomerCommand);
        return ResponseEntity.noContent().build();
    }
}
