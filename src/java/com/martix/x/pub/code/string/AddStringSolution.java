package com.martix.x.pub.code.string;

/**
 * Created by Andrew-Geng on 12:00 上午 2021/6/28
 *
 * 字符串相加 lc 415
 * 给定两个字符串形式的非负整数num1 和num2，计算它们的和。
 *
 * 提示：
 *
 * num1 和num2的长度都小于 5100
 * num1 和num2 都只包含数字0-9
 * num1 和num2 都不包含任何前导零
 * 你不能使用任何內建 BigInteger 库，也不能直接将输入的字符串转换为整数形式
 *
 */
public class AddStringSolution {

    /**
     *
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, temp = 0;

        StringBuffer res = new StringBuffer();
        while (i >= 0 || j >= 0 || temp != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;

            int result = x + y + temp;

            res.append(result % 10);
            temp = result / 10;

            i--;
            j--;
        }

        // 计算完以后的答案需要翻转过来
        res.reverse();
        return res.toString();
    }
}
