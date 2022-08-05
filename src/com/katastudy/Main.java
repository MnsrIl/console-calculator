package com.katastudy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String expression = scanner.nextLine();

            String result = calc(expression);

            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        test();
    }

    public static String calc(String input) {
        String[] parts = input.split(" ");

        String rawA = parts[0], rawB = parts[2], expression = parts[1];

        boolean isRoman = CalcUtils.getNumbersType(rawA, rawB).equals(CalcUtils.ROMAN);

        int a = CalcUtils.toArabic(rawA);
        int b = CalcUtils.toArabic(rawB);

        CalcUtils.checkRange(a);
        CalcUtils.checkRange(b);

        int result = CalcUtils.applyExpression(a, b, expression);

        return isRoman ? CalcUtils.toRome(result) : String.valueOf(result);
    }

    private static void test() {
        assert calc("9 + 1").equals("10"): "(1) Invalid expression with plus";
        assert calc("10 - 1").equals("9"): "(2) Invalid expression with minus";
        assert calc("10 / 5").equals("2"): "(3) Invalid expression with divide";
        assert calc("2 * 6").equals("12"): "(4) Invalid expression with multiply";

        assert calc("X + X").equals("XX"): "(5) Invalid rome numbers expression with plus";
        assert calc("X - V").equals("V"): "(6) Invalid rome numbers expression with minus";
        assert calc("VI / III").equals("II"): "(7) Invalid rome numbers expression with divide";
        assert calc("IX * VIII").equals("LXXII"): "(8) Invalid rome numbers expression with multiply";
    }
}
