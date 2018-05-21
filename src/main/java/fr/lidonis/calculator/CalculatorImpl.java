package fr.lidonis.calculator;

public class CalculatorImpl implements Calculator {

    @Override
    public int evaluate(String expression) {
        String[] integerToAdd = expression.split("\\+");
        return Integer.parseInt(integerToAdd[0]) + Integer.parseInt(integerToAdd[1]);
    }

}
