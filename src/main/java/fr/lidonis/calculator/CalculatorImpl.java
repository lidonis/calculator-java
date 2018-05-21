package fr.lidonis.calculator;

import java.math.BigDecimal;
import java.util.Stack;

public class CalculatorImpl implements Calculator {

    private Stack<BigDecimal> numbers = new Stack<>();
    private Stack<Operator> operators = new Stack<>();

    @Override
    public BigDecimal evaluate(String expression) {
        char[] tokens = expression.toCharArray();

        for (int i = 0; i < tokens.length; i++) {
            i = extractNumber(tokens, i);
            if (i == tokens.length) break;
            Operator.getOperator(tokens[i]).ifPresent(operator -> operators.push(operator));
        }

        while (!operators.empty())
            numbers.push(operators.pop().apply(numbers.pop(), numbers.pop()));

        return numbers.pop();
    }

    private int extractNumber(char[] tokens, int start) {
        if (isNumberPart(tokens[start])) {
            StringBuilder stringBuilder = new StringBuilder();
            while (start < tokens.length && (isNumberPart(tokens[start])))
                stringBuilder.append(tokens[start++]);
            numbers.push(new BigDecimal(stringBuilder.toString()));
        }
        return start;
    }

    private static boolean isNumberPart(char token) {
        return token >= '0' && token <= '9';
    }
}
