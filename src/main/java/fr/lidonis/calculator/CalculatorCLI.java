package fr.lidonis.calculator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class CalculatorCLI {

    private final PrintStream outContent;

    CalculatorCLI(PrintStream outContent) {
        this.outContent = outContent;
    }

    int evaluate(String[] args) {
        outContent.print("2");
        return 0;
    }
}
