package fr.lidonis.calculator.cli;

import fr.lidonis.calculator.domain.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class CalculatorCLITest {

    @Test
    void given_null_print_stream_it_should_throw_npe() {
        Throwable throwable = catchThrowable(() ->
                new CalculatorCLI(null, new PrintStream(new ByteArrayOutputStream()), new TestCalculator()));
        assertThat(throwable).isInstanceOf(NullPointerException.class).hasMessage("Out stream is required");
    }

    @Test
    void given_null_err_stream_it_should_throw_npe() {
        Throwable throwable = catchThrowable(() ->
                new CalculatorCLI(new PrintStream(new ByteArrayOutputStream()), null, new TestCalculator()));
        assertThat(throwable).isInstanceOf(NullPointerException.class).hasMessage("Err stream is required");
    }

    @Test
    void given_null_calculator_it_should_throw_npe() {
        Throwable throwable = catchThrowable(() ->
                new CalculatorCLI(new PrintStream(new ByteArrayOutputStream()),
                        new PrintStream(new ByteArrayOutputStream()),
                        null));
        assertThat(throwable).isInstanceOf(NullPointerException.class).hasMessage("Calculator stream is required");
    }


    @Nested
    @DisplayName("Tests with initialized CalculatorCLI")
    class Initialized {

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        CalculatorCLI calculatorCLI;

        @BeforeEach
        void setUp() {
            calculatorCLI = new CalculatorCLI(new PrintStream(outContent), new PrintStream(errContent), new TestCalculator());
        }

        @Test()
        void given_null_args_should_print_error_message_containing_null() {
            int exitCode = calculatorCLI.evaluate(null);
            assertThat(exitCode).isGreaterThan(0);
            assertThat(errContent.toString()).contains("null");
        }

        @Test()
        void given_empty_args_should_print_error_message_containing_empty() {
            int exitCode = calculatorCLI.evaluate(new String[]{});
            assertThat(exitCode).isGreaterThan(0);
            assertThat(errContent.toString()).contains("empty");
        }

        @Test
        void given_more_than_1_arg_should_print_error_message_containing_too_many_arguments() {
            int exitCode = calculatorCLI.evaluate(new String[]{"1+1", "2+2"});
            assertThat(exitCode).isGreaterThan(0);
            assertThat(errContent.toString()).contains("too many arguments");
        }

        @Test
        void given_empty_expression_should_print_error_message_containing_empty() {
            int exitCode = calculatorCLI.evaluate(new String[]{""});
            assertThat(exitCode).isGreaterThan(0);
            assertThat(errContent.toString()).contains("empty");
        }

        @Test
        void given_1_plus_1_it_should_print_2() {
            int exitCode = calculatorCLI.evaluate(new String[]{"1+1"});
            assertThat(exitCode).isEqualTo(0);
            assertThat(outContent.toString()).isEqualTo("2");
        }

        @Test
        void given_null_expression_should_print_error_message_containing_npe() {
            int exitCode = calculatorCLI.evaluate(new String[]{null});
            assertThat(exitCode).isGreaterThan(0);
            assertThat(errContent.toString()).contains("NullPointerException");
        }
    }
}

class TestCalculator implements Calculator {

    @Override
    public BigDecimal evaluate(String expression) {
        if (expression.isEmpty()) {
            throw new IllegalArgumentException("The expression should not be empty");
        } else {
            return new BigDecimal("2");
        }
    }
}