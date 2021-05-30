package com.hollow.microservices.customer.nosql.document;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customers")
public class CustomerMongoDocument {

    @Id
    String id;
    Integer age;
    String firstName;
    String lastName;

}
