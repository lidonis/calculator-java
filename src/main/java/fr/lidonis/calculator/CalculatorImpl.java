package fr.lidonis.calculator;

import java.math.BigDecimal;

public class CalculatorImpl implements Calculator {

    @Override
    public BigDecimal evaluate(String expression) {
        String[] integerToAdd = expression.split("\\+");
        return new BigDecimal(integerToAdd[0]).add(new BigDecimal(integerToAdd[1]));
    }

}
