package com.martix.x.validate;

/**
 * Created by ayue on 下午4:09 2018/7/6
 * <p>
 * 验证给定的字符串是否为数字。
 * <p>
 * 例如:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * <p>
 * 说明: 我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。
 * <p>
 * <p>
 * 解题思路：
 * <p>
 * 1、如果是指数表现法，e后不能有小数
 * <p>
 * 2、数字中有带有正负表示只能有一个
 * <p>
 * 3、小数时点只能有一个
 */
public class NumberValidateSolution {


    public boolean isNumber(String s) {
        s = s.trim();
        int len = s.length();
        if (0 == len) return false;

        boolean hasE = false, hasDot = false, hasDigit = false, hasFirst = false;

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                hasFirst = hasDigit = true;
                continue;
            }

            switch (c) {
                case 'e':
                    if (hasE || !hasDigit) return false;
                    hasE = true;

                    hasDot = true;
                    hasFirst = hasDigit = false;
                    break;
                case '.':
                    if (hasDot) return false;
                    hasDot = true;
                    hasFirst = true;
                    break;
                case '+':
                case '-':
                    if (hasFirst) return false;
                    hasFirst = true;
                    break;
                default:
                    return false;
            }
        }

        return hasDigit;
    }
}
