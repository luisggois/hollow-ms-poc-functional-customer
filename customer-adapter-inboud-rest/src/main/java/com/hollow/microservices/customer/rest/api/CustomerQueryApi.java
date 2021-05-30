package com.hollow.microservices.customer.rest.api;

import com.hollow.microservices.customer.rest.dto.CustomerDto;
import com.hollow.microservices.recurrent.basic.pagination.rest.PageApiSignatureParameters;
import com.hollow.microservices.recurrent.basic.rest.api.BaseApi;
import com.hollow.microservices.recurrent.basic.shell.ShellChannel;
import com.hollow.microservices.recurrent.errorhandling.exception.BaseException;
import com.hollow.microservices.recurrent.errorhandling.validation._enum.AllowedEnum;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Tag(name = "customer") @RequestMapping(path = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public interface CustomerQueryApi extends BaseApi {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Parameter(name = "customer.lastName", in = ParameterIn.QUERY, array = @ArraySchema(schema = @Schema(type = "string")))
    ResponseEntity<Collection<CustomerDto>> findAll(
            @RequestParam(name = PageApiSignatureParameters.PARAM_OFFSET, defaultValue = PageApiSignatureParameters.DEFAULT_OFFSET_STR) long offset,
            @RequestParam(name = PageApiSignatureParameters.PARAM_LIMIT, defaultValue = PageApiSignatureParameters.DEFAULT_LIMIT_STR) int limit,
            @RequestParam(name = PageApiSignatureParameters.PARAM_ASC, required = false) List<String> orderByAscProperties,
            @RequestParam(name = PageApiSignatureParameters.PARAM_DESC, required = false) List<String> orderByDescProperties,
            @Parameter(hidden = true) @RequestParam(required = false) Map<String, String> filters,
            HttpServletRequest request, HttpServletResponse response, UriComponentsBuilder uriBuilder) throws BaseException;

    @GetMapping(path = "/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<CustomerDto> findById(@PathVariable(CustomerQueryApi.SignatureParameters.CUSTOMER_ID) String customerId) throws BaseException;

    @UtilityClass
    class SignatureParameters {
        final String CUSTOMER_ID = "customerId";
    }

}
