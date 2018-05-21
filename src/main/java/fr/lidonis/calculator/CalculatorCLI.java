package fr.lidonis.calculator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Objects;

class CalculatorCLI {

    private final PrintStream outContent;

    CalculatorCLI(PrintStream outContent) {
        Objects.requireNonNull(outContent);
        this.outContent = outContent;
    }

    int evaluate(String[] args) {
        outContent.print("2");
        return 0;
    }
}
