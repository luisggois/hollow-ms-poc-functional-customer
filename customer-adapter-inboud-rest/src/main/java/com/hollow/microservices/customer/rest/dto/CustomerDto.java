package com.hollow.microservices.customer.rest.dto;


import lombok.Value;

@Value
public class CustomerDto {

    String id;
    Integer age;
    String firstName;
    String lastName;

}

