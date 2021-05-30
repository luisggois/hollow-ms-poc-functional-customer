package com.hollow.microservices.customer.rest.api;

import com.hollow.microservices.customer.rest.dto.CreateCustomerRequestDto;
import com.hollow.microservices.customer.rest.dto.CustomerDto;
import com.hollow.microservices.recurrent.basic.rest.api.BaseApi;
import com.hollow.microservices.recurrent.basic.shell.ShellChannel;
import com.hollow.microservices.recurrent.errorhandling.exception.BaseException;
import com.hollow.microservices.recurrent.errorhandling.validation._enum.AllowedEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "customers")
@RequestMapping(path = "/customers")
public interface CustomerCommandApi extends BaseApi {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<CustomerDto> create(
            @Valid @RequestBody CreateCustomerRequestDto createCustomerRequestDto,
            @RequestHeader("X-Channel") @AllowedEnum(enumClass = ShellChannel.class) String channel
    );

    @DeleteMapping(path = "/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> deleteById(@PathVariable(CustomerCommandApi.SignatureParameters.CUSTOMER_ID) Long customerId) throws BaseException;

    @UtilityClass
    class SignatureParameters {
        final String CUSTOMER_ID = "customerId";
    }

}