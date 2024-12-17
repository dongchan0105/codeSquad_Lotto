package org.application.util;

public class Parser {

    private static final String DELIMITER = ",";

    public static int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만이 입력될 수 있습니다");
        }
    }

    public static String[] splitWithDelimiter(String input) {
        return input.split(DELIMITER);
    }
}