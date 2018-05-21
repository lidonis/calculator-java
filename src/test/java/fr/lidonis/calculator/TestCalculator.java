package fr.lidonis.calculator;

import java.math.BigDecimal;

public class TestCalculator implements Calculator {

    @Override
    public BigDecimal evaluate(String expression) {
        if (expression.isEmpty()) {
            throw new IllegalArgumentException("The expression should not be empty");
        } else {
            return new BigDecimal("2");
        }
    }
}
