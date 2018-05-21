package fr.lidonis.calculator;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorCLITest {

    @Test
    void given_1_plus_1_it_should_print_2() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        CalculatorCLI calculatorCLI = new CalculatorCLI(new PrintStream(outContent));
        int evaluate = calculatorCLI.evaluate(new String[]{"1+1"});
        assertThat(evaluate).isEqualTo(0);
        assertThat(outContent.toString()).isEqualTo("2");
    }

}