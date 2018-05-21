package fr.lidonis.calculator;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Stack;

public class CalculatorImpl implements Calculator {

    private Stack<BigDecimal> numbers = new Stack<>();
    private Stack<Operator> operators = new Stack<>();

    @Override
    public BigDecimal evaluate(String expression) {
        checkExpression(expression);
        char[] tokens = expression.toCharArray();
        NumberExtractor numberExtractor = new NumberExtractor(tokens);

        for (int i = 0; i < tokens.length; i++) {
            NumberExtractor.Extract extract = numberExtractor.extractNumber(i);
            i = extract.getPosition();
            numbers.push(extract.getNumber());
            // stop if we reach the last number
            if (i != tokens.length) {
                pushOperator(tokens[i]);
            }
        }

        applyStackedOperators();

        return numbers.pop();
    }


    private void checkExpression(String expression) {
        if (expression == null) {
            throw new IllegalArgumentException("The expression should not be null");
        } else if (expression.isEmpty()) {
            throw new IllegalArgumentException("The expression should not be empty");
        }
    }

    private void pushOperator(char token) {
        Optional<Operator> optionalOperator = Operator.getOperator(token);
        if (optionalOperator.isPresent()) {
            Operator operator = optionalOperator.get();
            while (!operators.empty() && operator.hasPrecedence(operators.peek())) {
                applyOperator();
            }
            operators.push(operator);
        }
    }

    private void applyStackedOperators() {
        while (!operators.empty()) {
            applyOperator();
        }
    }

    private void applyOperator() {
        if (numbers.size() < 2) {
            throw new IllegalArgumentException("The expression is invalid");
        }
        numbers.push(operators.pop().apply(numbers.pop(), numbers.pop()));
    }
}
