package org.application.util;

public class InputValidator {
    public static String validateEmpty(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("빈 값이 입력 되었습니다");
        }

        return input;
    }
}
