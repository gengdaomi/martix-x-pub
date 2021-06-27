package com.martix.x.pub.string;

/**
 * Created by Andrew-Geng on 12:55 上午 2021/6/28
 *
 * 36进制由0-9，a-z，共36个字符表示。
 *
 * 要求按照加法规则计算出任意两个36进制正整数的和，如1b + 2x = 48 （解释：47+105=152）
 *
 * 要求：不允许使用先将36进制数字整体转为10进制，相加后再转回为36进制的做法
 */
public class Add36StringSolution {

    private static final String chars = "0123456789abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        System.out.println(add_36("12", "12"));
    }

    /**
     * 不论是几进制加法，方法都是从两个字符串的末尾开始遍历，将个位数加进 stringbuilder，若有进位则记录（供下次两字符相加时加上这个进位），
     * 最后再将 stringbuilder 反转
     *
     * @param a
     * @param b
     * @return
     */
    public static String add_36(String a, String b){
        int alength = a.length();
        int blength = b.length();

        int m = Math.max(a.length(), blength);
        int temp = 0;
        int charLength = chars.length();

        String result = "";
        for (int i = 0; i < m; i++) {
            int x = i < alength ? chars.indexOf(a.charAt(alength - i - 1)) : 0;
            int y = i < blength ? chars.indexOf(b.charAt(blength - i - 1)) : 0;

            int sum = x + y + temp;
            if (sum > charLength) {
                temp = sum / charLength;
            }

            result = chars.charAt(sum % charLength) + result;
        }

        if (temp > 0) {
            result = chars.charAt(temp) + result;
        }

        return result;
    }
}
