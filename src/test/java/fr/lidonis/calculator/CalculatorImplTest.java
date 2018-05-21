package fr.lidonis.calculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorImplTest {

    @Test
    void given_1_plus_1_should_return_2() {
        int result = new CalculatorImpl().evaluate("1+1");
        assertThat(result).isEqualTo(2);
    }

    @Test
    void given_5_plus_10_should_return_2() {
        int result = new CalculatorImpl().evaluate("5+10");
        assertThat(result).isEqualTo(15);
    }
}