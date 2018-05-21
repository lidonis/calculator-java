package fr.lidonis.calculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class CalculatorImplTest {

    @Test
    void given_1_plus_1_return_2() {
        BigDecimal result = new CalculatorImpl().evaluate("1+1");
        assertThat(result).isEqualTo(new BigDecimal(2));
    }

    @Test
    void given_5_plus_10_return_2() {
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

    @Test
    void given_5_minus_2_return_3() {
        BigDecimal result = new CalculatorImpl().evaluate("5-2");
        assertThat(result).isEqualTo("3");
    }

    @Test
    void given_5_minus_2_plus_5_minus_4_return_4() {
        BigDecimal result = new CalculatorImpl().evaluate("5-2+5-4");
        assertThat(result).isEqualTo("4");
    }

    @Test
    void given_9_times_3_return_27() {
        BigDecimal result = new CalculatorImpl().evaluate("9*3");
        assertThat(result).isEqualTo("27");
    }

    @Test
    void given_10_divided_by_2_return_5() {
        BigDecimal result = new CalculatorImpl().evaluate("10/2");
        assertThat(result).isEqualTo("5");
    }

    @Test
    void given_5_divided_by_0_throw_exception_containing_divide_by_zero() {
        Throwable throwable = catchThrowable(() -> new CalculatorImpl().evaluate("5/0"));
        assertThat(throwable).hasMessageContaining("divide by zero");
    }

    @Test
    void given_3_times_2_plus_1_divided_by_5_return_6_point_2() {
        BigDecimal result = new CalculatorImpl().evaluate("3*2+1/5");
        assertThat(result).isEqualTo("6.2");
    }

    @Test
    void given_5_point_2_plus_1_return_6_point_2() {
        BigDecimal result = new CalculatorImpl().evaluate("5.2+1");
        assertThat(result).isEqualTo("6.2");
    }

    @Test
    void given_5point_2_point_8_throw_exception_containing_more_than_one_decimal_point() {
        Throwable throwable = catchThrowable(() -> new CalculatorImpl().evaluate("5.2.8"));
        assertThat(throwable).hasMessageContaining("more than one point");
    }

    @Test
    void given_null_expression_throw_exception_containing_null() {
        Throwable throwable = catchThrowable(() -> new CalculatorImpl().evaluate(null));
        assertThat(throwable).hasMessageContaining("null");
    }

    @Test
    void given_empty_expression_throw_exception_containing_empty() {
        Throwable throwable = catchThrowable(() -> new CalculatorImpl().evaluate(""));
        assertThat(throwable).hasMessageContaining("empty");
    }

    @ParameterizedTest
    @ValueSource(strings = {"+", "a", "1+", "1a"})
    void given_invalid_expression_throw_exception_containing_invalid(String expression) {
        Throwable throwable = catchThrowable(() -> new CalculatorImpl().evaluate(expression));
        assertThat(throwable).hasMessageContaining("invalid");
    }

    @Test
    void given_1_plus_1_with_spaces_return_2() {
        BigDecimal result = new CalculatorImpl().evaluate("1 + 1");
        assertThat(result).isEqualTo(new BigDecimal(2));
    }
}