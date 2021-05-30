package com.hollow.microservices.customer.core.adapter;

import com.hollow.microservices.customer.core.domain.Customer;
import com.hollow.microservices.customer.core.port.in.CustomerQuery;
import com.hollow.microservices.customer.core.port.out.CustomerRemotePort;
import com.hollow.microservices.recurrent.basic.pagination.domain.IPageElement;
import com.hollow.microservices.recurrent.basic.pagination.rest.IPageSort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerQueryAdapter implements CustomerQuery {

    private final CustomerRemotePort port;

    public CustomerQueryAdapter(@Qualifier("customerPersistenceAdapterNoSql") CustomerRemotePort port) {
        this.port = port;
    }

    @Override
    public IPageElement<Customer> findAll(long offset, int limit, IPageSort.Direction sortDirection, List<String> predominantSortList) {
        return this.port.findAll(offset, limit, sortDirection, predominantSortList);
    }

    @Override
    public Optional<Customer> findById(String customerId) {
        return this.port.findById(customerId);
    }

}
