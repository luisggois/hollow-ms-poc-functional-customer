package com.hollow.microservices.customer.rest.controller;

import com.hollow.microservices.customer.core.domain.Customer;
import com.hollow.microservices.customer.core.port.in.CustomerQuery;
import com.hollow.microservices.customer.rest.api.CustomerQueryApi;
import com.hollow.microservices.customer.rest.dto.CustomerDto;
import com.hollow.microservices.customer.rest.mapper.CustomerQueryMapper;
import com.hollow.microservices.recurrent.basic.pagination.domain.IPageElement;
import com.hollow.microservices.recurrent.basic.pagination.rest.IPageNavigation;
import com.hollow.microservices.recurrent.basic.pagination.rest.IPageSort;
import com.hollow.microservices.recurrent.basic.pagination.rest.PageNavigation;
import com.hollow.microservices.recurrent.basic.pagination.rest.PageSort;
import com.hollow.microservices.recurrent.errorhandling.exception.BaseException;
import com.hollow.microservices.recurrent.errorhandling.exception.business.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequiredArgsConstructor
public class CustomerQueryController implements CustomerQueryApi {

    @Value("${pagination.max-limit}")
    private int maxLimit;

    private final CustomerQuery customerQuery;
    private final CustomerQueryMapper customerQueryMapper;

    @Override
    public ResponseEntity<Collection<CustomerDto>> findAll(long offset, int limit, List<String> orderByAscProperties, List<String> orderByDescProperties, Map<String, String> filters,
                                                           HttpServletRequest request, HttpServletResponse response, UriComponentsBuilder uriComponentsBuilder) {

        IPageSort pageSort = new PageSort(orderByAscProperties, orderByDescProperties);

        IPageNavigation pageNavigation = new PageNavigation();

        int evaluatedLimit = pageNavigation.evaluateLimit(limit, this.maxLimit);

        IPageElement<Customer> customerPageElement = this.customerQuery.findAll(offset, evaluatedLimit, pageSort.getSortDirection(), pageSort.getPredominantSortList());

        List<CustomerDto> customerDtoList = this.customerQueryMapper.toCustomerDtoList(customerPageElement.getElements());

        pageNavigation.addXTotalCountHeader(response, customerPageElement.getTotalElements());
        pageNavigation.addLinkHeaderOnPagedResourceRetrieval(uriComponentsBuilder.replacePath(request.getRequestURI()), response, offset, evaluatedLimit,
                customerPageElement.getTotalElements(), pageSort.getPredominantSortList(), pageSort.getSortDirection());

        return ResponseEntity.ok(customerDtoList);
    }

    @Override
    public ResponseEntity<CustomerDto> findById(String customerId) throws BaseException {
        Customer customer = this.customerQuery.findById(customerId)
                .orElseThrow(() -> ResourceNotFoundException.builder().id(customerId).build());

        CustomerDto customerDto = this.customerQueryMapper.toCustomerDto(customer);

        return ResponseEntity.ok(customerDto);
    }
}
