package fr.lidonis.calculator;

public class CalculatorMain {

    public static void main(String[] args) {
        CalculatorCLI calculatorCLI = new CalculatorCLI(System.out, System.err, new CalculatorImpl());
        System.exit(calculatorCLI.evaluate(args));
    }
}