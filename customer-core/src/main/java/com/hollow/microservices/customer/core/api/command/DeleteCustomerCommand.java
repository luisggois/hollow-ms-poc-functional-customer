package com.hollow.microservices.customer.core.api.command;

import com.hollow.microservices.recurrent.errorhandling.validation.SelfValidator;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class DeleteCustomerCommand extends SelfValidator<DeleteCustomerCommand> {

    @NotNull
    Long id;

    @Builder
    public DeleteCustomerCommand(Long id) {
        this.id = id;
        this.selfValidate();
    }
}
