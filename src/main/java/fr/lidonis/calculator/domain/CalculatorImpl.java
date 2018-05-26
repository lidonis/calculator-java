package fr.lidonis.calculator.domain;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

public class CalculatorImpl implements Calculator {

    private Deque<BigDecimal> numbers = new ArrayDeque<>();
    private Deque<Operator> operators = new ArrayDeque<>();

    @Override
    public BigDecimal evaluate(String expression) {
        checkExpression(expression);
        String expressionWithoutSpace = removeSpaces(expression);
        parseTokens(expressionWithoutSpace.toCharArray());
        applyStackedOperators();
        return numbers.pop();
    }

    private String removeSpaces(String expression) {
        return expression.replaceAll(" ", "");
    }

    private void checkExpression(String expression) {
        if (expression == null) {
            throw new IllegalArgumentException("The expression should not be null");
        } else if (expression.isEmpty()) {
            throw new IllegalArgumentException("The expression should not be empty");
        }
    }

    private void parseTokens(char[] tokens) {
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
    }

    private void pushOperator(char token) {
        Optional<Operator> optionalOperator = Operator.getOperator(token);
        if (optionalOperator.isPresent()) {
            Operator operator = optionalOperator.get();
            while (!operators.isEmpty() && operator.hasPrecedence(operators.peek())) {
                applyOperator(operators.pop());
            }
            operators.push(operator);
        } else {
            throw new IllegalArgumentException("The expression is invalid");
        }
    }

    private void applyStackedOperators() {
        while (!operators.isEmpty()) {
            applyOperator(operators.pop());
        }
    }

    private void applyOperator(Operator operator) {
        if (numbers.size() < 2) {
            throw new IllegalArgumentException("The expression is invalid");
        }
        // put the numbers in the right because they are inverted in the stack
        BigDecimal number2 = numbers.pop();
        numbers.push(operator.apply(numbers.pop(), number2));
    }
}
