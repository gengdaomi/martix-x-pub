package com.martix.x.pub.code.string;

/**
 * 字符串转数字
 */
public class StringToIntSolution {

    public int myAtoi(String str) {
        int min = -Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;

        long result = 0;
        str = str.trim();
        int length = str.length();

        if (length < 1) {
            return 0;
        }

        int start = 0;
        boolean negative = false;

        if (str.charAt(start) == '+' || str.charAt(start) == '-') {
            if (str.charAt(start) == '-') {
                negative = true;
            }

            start++;
        }

        for (int i = start; i < length; i++) {
            char ch = str.charAt(i);

            if (ch < '0' || ch > '9') {
                break;
            }

            result = 10 * result + (ch - '0');
            if (!negative && result > max) {
                return max;
            }

            if (negative && -result < min) {
                return min;
            }
        }

        if (negative) {
            result = -result;
        }

        return (int) result;
    }

    public static void main(String[] args) {
        Integer i = new StringToIntSolution().myAtoi("-42ff ");
        System.out.println(i);
    }

}
