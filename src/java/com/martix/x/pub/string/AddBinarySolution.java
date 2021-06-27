package com.martix.x.pub.string;

/**
 * Created by Andrew-Geng on 11:27 下午 2021/6/27
 * 二进制求和 lc 67
 * <p>
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * <p>
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 * <p>
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *  
 * <p>
 * 提示：
 * <p>
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 */
public class AddBinarySolution {

    public static void main(String[] args) {
        String result = new AddBinarySolution().addBinary_1("1010", "1011");
        System.out.println(result);
    }

    /**
     * 不论是几进制加法，方法都是从两个字符串的末尾开始遍历，将个位数加进 stringbuilder，若有进位则记录（供下次两字符相加时加上这个进位），
     * 最后再将 stringbuilder 反转
     * <p>
     * 核心思路：末尾对齐，逐位相加。在十进制的计算中「逢十进一」，二进制中我们需要「逢二进一」
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        StringBuffer result = new StringBuffer();

        int n = Math.max(a.length(), b.length()), temp = 0;

        for (int i = 0; i < n; ++i) {
            int x = i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            int y = i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;

            int sum = x + y + temp;

            result.append((char) (sum % 2 + '0'));
            temp = sum / 2;
        }

        if (temp > 0) {
            result.append('1');
        }

        result.reverse(); //将倒置的结果再反转

        return result.toString();
    }

    /**
     * 较为粗暴的方式 ，利用java自带的函数先将二进制转为十进制，相加后再转为二进制
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary_1(String a, String b) {
        return Integer.toBinaryString(Integer.parseInt(a, 2) + Integer.parseInt(b, 2));
    }
}
