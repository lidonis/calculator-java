package fr.lidonis.calculator;

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
            return new Extract(start, new BigDecimal(stringBuilder.toString()));
        }
        return null;
    }

    private static boolean isNumberPart(char token) {
        return token >= '0' && token <= '9';
    }

    class Extract {
        private int position;
        private BigDecimal number;

        Extract(int position, BigDecimal number) {
            this.position = position;
            this.number = number;
        }

        int getPosition() {
            return position;
        }

        BigDecimal getNumber() {
            return number;
        }
    }
}
