package com.hollow.microservices.customer.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private String id;
    private Integer age;
    private String firstName;
    private String lastName;

}
