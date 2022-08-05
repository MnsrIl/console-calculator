package com.katastudy;

import java.util.List;

abstract class CalcUtils {
    public static final String ROMAN = "Roman";
    public static final String ARABIC = "Arabic";

    private static final String INVALID_FORMAT = "Both values should be the same format";
    private static final String INVALID_INPUT_RANGE = "Values should be in range [1 ... 10]";
    private static final String INVALID_ROMAN_RESULT = "Roman result value should be in range (0,4000]";

    public static String getNumbersType(String a, String b) {
        String formattedA = String.valueOf(toArabic(a));
        String formattedB = String.valueOf(toArabic(b));

        String firstValueType = formattedA.equals(a) ? ARABIC : ROMAN;
        String secondValueType = formattedB.equals(b) ? ARABIC : ROMAN;

        boolean isSameType = firstValueType.equals(secondValueType);

        if (!isSameType) {
            throw new IllegalArgumentException(INVALID_FORMAT);
        }

        return firstValueType.equals(ROMAN) ? ROMAN : ARABIC;
    }

    public static void checkRange(int value) {
        if (value <= 0 || value > 10) {
            throw new IllegalArgumentException(INVALID_INPUT_RANGE);
        }
    }

    public static String toRome(int value) {
        if ((value <= 0) || (value > 4000)) {
            throw new IllegalArgumentException(INVALID_ROMAN_RESULT);
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((value > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= value) {
                sb.append(currentSymbol.name());
                value -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    public static int toArabic(String value) {
        return switch (value) {
            case "X" -> 10;
            case "IX" -> 9;
            case "VIII" -> 8;
            case "VII" -> 7;
            case "VI" -> 6;
            case "V" -> 5;
            case "IV" -> 4;
            case "III" -> 3;
            case "II" -> 2;
            case "I" -> 1;
            default -> Integer.parseInt(value);
        };
    }

    public static int applyExpression(int a, int b, String expression) {
        return switch (expression) {
            case "/", ":" -> a / b;
            case "*" -> a * b;
            case "+" -> a + b;
            case "-" -> a - b;
            default -> 0;
        };
    }
}
