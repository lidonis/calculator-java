package fr.lidonis.calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public enum Operator {
    ADD('+', BigDecimal::add),
    SUBTRACT('-', BigDecimal::subtract),
    MULTIPLY('*', BigDecimal::multiply),
    DIVIDE('/', (bigDecimal, bigDecimal2) -> {
        if (BigDecimal.ZERO.equals(bigDecimal2))
            throw new
                    UnsupportedOperationException("Cannot divide by zero");
        return bigDecimal.divide(bigDecimal2, MathContext.DECIMAL128);
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
