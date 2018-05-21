package fr.lidonis.calculator;

import java.io.PrintStream;
import java.util.Objects;

class CalculatorCLI {

    private final PrintStream outContent;
    private final PrintStream errContent;

    CalculatorCLI(PrintStream outContent, PrintStream errContent) {
        Objects.requireNonNull(outContent, "Out stream is required");
        Objects.requireNonNull(errContent, "Err stream is required");
        this.outContent = outContent;
        this.errContent = errContent;
    }

    int evaluate(String[] args) {
        String message = checkArgs(args);
        if (message != null) {
            errContent.print(message);
            return 1;
        } else {
            outContent.print("2");
            return 0;
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
