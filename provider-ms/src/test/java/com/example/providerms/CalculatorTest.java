package com.example.providerms;

import com.example.providerms.utils.Calculator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CalculatorTest {
    @Test
    public void testAdd() {
        int result = Calculator.add(5, 3);
        assertEquals(8, result, "The add method should add two numbers correctly");
    }

}
