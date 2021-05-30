package com.hollow.microservices.customer.rest.dto;

import com.hollow.microservices.recurrent.errorhandling.validation.SelfValidator;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class CreateCustomerRequestDto extends SelfValidator<CreateCustomerRequestDto> {

    @NotNull
    Integer age;
    @NotBlank
    String firstName;
    @NotBlank
    String lastName;

    @Builder
    public CreateCustomerRequestDto(Integer age, String firstName, String lastName) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.selfValidate();
    }

}
