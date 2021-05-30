package com.hollow.microservices.customer.core.api.command;

import com.hollow.microservices.recurrent.errorhandling.validation.SelfValidator;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class  CreateCustomerCommand extends SelfValidator<CreateCustomerCommand> {

    String id;
    @NotNull
    Integer age;
    @NotBlank
    String firstName;
    @NotBlank
    String lastName;
    @NotBlank
    String channel;

    @Builder
    CreateCustomerCommand(String id, Integer age, String firstName, String lastName, String channel) {
        this.id = id;
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.channel = channel;
        this.selfValidate();
    }

}
