package com.hollow.microservices.customer.core.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void add() {
        Calculator calculator = new Calculator();
        int answer = calculator.add();
        Assertions.assertEquals(answer, 2);
    }
}