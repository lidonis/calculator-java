package fr.lidonis.calculator;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorImplTest {

    @Test
    void given_1_plus_1_should_return_2() {
        BigDecimal result = new CalculatorImpl().evaluate("1+1");
        assertThat(result).isEqualTo(new BigDecimal(2));
    }

    @Test
    void given_5_plus_10_should_return_2() {
        BigDecimal result = new CalculatorImpl().evaluate("5+10");
        assertThat(result).isEqualTo(new BigDecimal(15));
    }

    @Test
    void given_111111111111111111_plus_1_return_111111111111111112() {
        BigDecimal result = new CalculatorImpl().evaluate("111111111111111111+1");
        assertThat(result).isEqualTo("111111111111111112");
    }

    @Test
    void given_1_plus_2_plus_3_return_6() {
        BigDecimal result = new CalculatorImpl().evaluate("1+2+3");
        assertThat(result).isEqualTo("6");
    }

}