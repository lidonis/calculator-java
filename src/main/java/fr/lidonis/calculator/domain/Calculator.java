package fr.lidonis.calculator.domain;

import java.math.BigDecimal;

public interface Calculator {
    BigDecimal evaluate(String expression);
}
