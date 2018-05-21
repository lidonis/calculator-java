package fr.lidonis.calculator;

public class TestCalculator implements Calculator {

    @Override
    public int evaluate(String expression) {
        if (expression.isEmpty()) {
            throw new IllegalArgumentException("The expression should not be empty");
        } else {
            return 2;
        }
    }
}
