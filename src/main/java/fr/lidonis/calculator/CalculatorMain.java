package fr.lidonis.calculator;

public class CalculatorMain {

    public static void main(String[] args) {
        CalculatorCLI calculatorCLI = new CalculatorCLI(System.out);
        System.exit(calculatorCLI.evaluate(args));
    }
}
