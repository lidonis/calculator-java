package fr.lidonis.calculator;

import fr.lidonis.calculator.cli.CalculatorCLI;
import fr.lidonis.calculator.domain.CalculatorImpl;

public class CalculatorMain {

    public static void main(String[] args) {
        CalculatorCLI calculatorCLI = new CalculatorCLI(System.out, System.err, new CalculatorImpl());
        System.exit(calculatorCLI.evaluate(args));
    }
}
