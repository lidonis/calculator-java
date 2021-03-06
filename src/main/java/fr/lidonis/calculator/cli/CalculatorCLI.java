package fr.lidonis.calculator.cli;

import fr.lidonis.calculator.domain.Calculator;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Objects;

public class CalculatorCLI {

    private final PrintStream outContent;
    private final PrintStream errContent;
    private final Calculator calculator;

    public CalculatorCLI(PrintStream outContent, PrintStream errContent, Calculator calculator) {
        Objects.requireNonNull(outContent, "Out stream is required");
        Objects.requireNonNull(errContent, "Err stream is required");
        Objects.requireNonNull(calculator, "Calculator stream is required");
        this.outContent = outContent;
        this.errContent = errContent;
        this.calculator = calculator;
    }

    public int evaluate(String[] args) {
        String message = checkArgs(args);
        if (message != null) {
            errContent.print(message);
            return 1;
        } else {
            try {
                BigDecimal result = calculator.evaluate(args[0]);
                outContent.print(result);
                return 0;
            } catch (Exception e) {
                if (e.getMessage() == null) {
                    errContent.print(e);
                } else {
                    errContent.print(e.getMessage());
                }
                return 1;
            }
        }
    }

    private String checkArgs(String[] args) {
        String errorMessage = null;
        if (args == null) {
            errorMessage = "Arguments should not be null";
        } else if (args.length == 0) {
            errorMessage = "Arguments should not be empty";
        } else if (args.length > 1) {
            errorMessage = "The command contains too many arguments";
        }
        return errorMessage;
    }
}
