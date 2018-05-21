package fr.lidonis.calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public enum Operator {
    ADD('+', BigDecimal::add),
    SUBTRACT('-', (bigDecimal, bigDecimal2) -> bigDecimal2.subtract(bigDecimal)),
    MULTIPLY('*', BigDecimal::multiply),
    DIVIDE('/', (bigDecimal, bigDecimal2) -> {
        if (BigDecimal.ZERO.equals(bigDecimal))
            throw new
                    UnsupportedOperationException("Cannot divide by zero");
        return bigDecimal2.divide(bigDecimal, MathContext.DECIMAL128);
    });

    char operator;
    private BinaryOperator<BigDecimal> operation;

    Operator(char operator, BinaryOperator<BigDecimal> operation) {
        this.operator = operator;
        this.operation = operation;
    }

    BigDecimal apply(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
        return operation.apply(bigDecimal1, bigDecimal2);
    }

    public boolean hasPrecedence(Operator op) {
        return (this != Operator.MULTIPLY && this != Operator.DIVIDE) || (op != Operator.ADD && op != Operator.SUBTRACT);
    }

    static Optional<Operator> getOperator(char c) {
        return Stream.of(values()).filter(operator1 -> operator1.operator == c).findAny();
    }
}
