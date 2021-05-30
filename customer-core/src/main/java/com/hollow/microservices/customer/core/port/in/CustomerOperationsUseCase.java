package com.hollow.microservices.customer.core.port.in;

import com.hollow.microservices.customer.core.api.command.CreateCustomerCommand;
import com.hollow.microservices.customer.core.api.command.DeleteCustomerCommand;
import com.hollow.microservices.customer.core.domain.Customer;
import com.hollow.microservices.recurrent.errorhandling.exception.BaseException;

public interface CustomerOperationsUseCase {

    Customer create(CreateCustomerCommand createCustomerCommand);

    void delete(DeleteCustomerCommand deleteCustomerCommand) throws BaseException;

}
