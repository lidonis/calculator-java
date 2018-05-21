package fr.lidonis.calculator;

import java.math.BigDecimal;
import java.util.Stack;

public class CalculatorImpl implements Calculator {

    private Stack<BigDecimal> numbers = new Stack<>();

    @Override
    public BigDecimal evaluate(String expression) {
        String[] numbersToAdd = expression.split("\\+");

        for (String number : numbersToAdd) {
            numbers.add(new BigDecimal(number));
        }

        while (numbers.size() > 1)
            numbers.push(numbers.pop().add(numbers.pop()));

        return numbers.pop();
    }
}
