package com.hollow.microservices.customer.nosql.adapter;

import com.hollow.microservices.customer.core.domain.Customer;
import com.hollow.microservices.customer.core.port.out.CustomerRemotePort;
import com.hollow.microservices.customer.nosql.document.CustomerMongoDocument;
import com.hollow.microservices.customer.nosql.mapper.CustomerNoSqlMapper;
import com.hollow.microservices.customer.nosql.repository.CustomerMongoRepository;
import com.hollow.microservices.recurrent.basic.pagination.domain.IPageElement;
import com.hollow.microservices.recurrent.basic.pagination.persistence.IPageFetcher;
import com.hollow.microservices.recurrent.basic.pagination.rest.IPageSort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("customerPersistenceAdapterNoSql")
@RequiredArgsConstructor
public class CustomerPersistenceAdapter implements CustomerRemotePort {

    private final IPageFetcher<Customer, CustomerMongoDocument, String, CustomerMongoRepository> customerPageFetcher;
    private final CustomerMongoRepository customerMongoRepository;
    private final CustomerNoSqlMapper customerNoSqlMapper;

    @Override
    public Customer create(Customer customer){
        return this.createOrUpdate(customer);
    }

    @Override
    public void deleteById(Long id) {
        this.customerMongoRepository.deleteById(String.valueOf(id));
    }

    @Override
    public boolean existsById(Long id) {
        return this.customerMongoRepository.existsById(String.valueOf(id));
    }

    @Override
    public IPageElement<Customer> findAll(long offset, int limit, IPageSort.Direction sortDirection, List<String> predominantSortList) {

        IPageFetcher.Parameters fetchingParameters = IPageFetcher.Parameters.builder()
                .offset(offset)
                .limit(limit)
                .sortDirection(sortDirection)
                .sortingProperties(predominantSortList)
                .build();

        return this.customerPageFetcher.fetchPageElement(fetchingParameters, this.customerMongoRepository, this.customerNoSqlMapper::toCustomerList);
    }

    @Override
    public Optional<Customer> findById(String id) {
        Optional<CustomerMongoDocument> customerMongoDocument = this.customerMongoRepository.findById(id);
        return customerMongoDocument.map(this.customerNoSqlMapper::toCustomer);
    }

    private Customer createOrUpdate(Customer customer){
        CustomerMongoDocument customerMongoDocument = this.customerNoSqlMapper.toCustomerDocument(customer);
        CustomerMongoDocument persistedCustomer = this.customerMongoRepository.save(customerMongoDocument);
        return this.customerNoSqlMapper.toCustomer(persistedCustomer);
    }

}
