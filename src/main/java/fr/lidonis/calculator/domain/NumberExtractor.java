package fr.lidonis.calculator.domain;

import java.math.BigDecimal;

class NumberExtractor {

    private char[] tokens;

    NumberExtractor(char[] tokens) {
        this.tokens = tokens;
    }

    Extract extractNumber(int start) {
        if (isNumberPart(tokens[start])) {
            StringBuilder stringBuilder = new StringBuilder();
            while (start < tokens.length && (isNumberPart(tokens[start])))
                stringBuilder.append(tokens[start++]);
            return new Extract(start, stringBuilder.toString());
        }
        throw new IllegalArgumentException("The expression is invalid");
    }

    private static boolean isNumberPart(char token) {
        return token >= '0' && token <= '9' || token == '.';
    }

    class Extract {
        private int position;
        private BigDecimal number;

        Extract(int position, String val) {
            this.position = position;
            if (pointCount(val) > 1) {
                throw new IllegalArgumentException("The expression contains a number with more than one point");
            }
            this.number = new BigDecimal(val);
        }

        private long pointCount(String val) {
            return val.chars().filter(ch -> ch == '.').count();
        }

        int getPosition() {
            return position;
        }

        BigDecimal getNumber() {
            return number;
        }
    }
}
