package com.hollow.microservices.customer.core.adapter;

import com.hollow.microservices.customer.core.api.command.CreateCustomerCommand;
import com.hollow.microservices.customer.core.api.command.DeleteCustomerCommand;
import com.hollow.microservices.customer.core.domain.Customer;
import com.hollow.microservices.customer.core.mapper.CustomerDomainMapper;
import com.hollow.microservices.customer.core.port.in.CustomerOperationsUseCase;
import com.hollow.microservices.customer.core.port.out.CustomerRemotePort;
import com.hollow.microservices.recurrent.errorhandling.exception.BaseException;
import com.hollow.microservices.recurrent.errorhandling.exception.business.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CustomerCommandAdapter implements CustomerOperationsUseCase {

    private final CustomerRemotePort port;
    private final CustomerDomainMapper mapper;

    public CustomerCommandAdapter(@Qualifier("customerPersistenceAdapterNoSql") CustomerRemotePort customerRemotePort, CustomerDomainMapper customerDomainMapper){
        this.port = customerRemotePort;
        this.mapper = customerDomainMapper;
    }

    @Override
    public Customer create(CreateCustomerCommand createCustomerCommand) {
        Customer customer = this.mapper.toCustomer(createCustomerCommand);
        return this.port.create(customer);
    }

    @Override
    public void delete(DeleteCustomerCommand deleteCustomerCommand) throws BaseException {
        Long id = deleteCustomerCommand.getId();
        boolean customerExists = this.port.existsById(id);
        if (!customerExists){
            throw ResourceNotFoundException.builder().id(String.valueOf(id)).build();
        }
        this.port.deleteById(id);
    }

}
