package com.hollow.microservices.customer.core.port.out;

import com.hollow.microservices.customer.core.domain.Customer;
import com.hollow.microservices.recurrent.basic.pagination.domain.IPageElement;
import com.hollow.microservices.recurrent.basic.pagination.rest.IPageSort;

import java.util.List;
import java.util.Optional;

public interface CustomerRemotePort {

    Customer create(Customer customer);

    void deleteById(Long id);

    boolean existsById(Long id);

    IPageElement<Customer> findAll(long offset, int limit, IPageSort.Direction sortDirection, List<String> predominantSortList);

    Optional<Customer> findById(String customerId);
}
