package com.martix.x.pub.code.validate;

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

    public boolean isNumber_1(String s){
        if(s == null || s.length() == 0){
            return false; // s为空对象或 s长度为0(空字符串)时, 不能表示数值
        }

        boolean isNum = false, isDot = false, ise_or_E = false; // 标记是否遇到数位、小数点、‘e’或'E'

        char[] str = s.trim().toCharArray();  // 删除字符串头尾的空格，转为字符数组，方便遍历判断每个字符

        for(int i=0; i<str.length; i++) {
            if(str[i] >= '0' && str[i] <= '9'){
                isNum = true; // 判断当前字符是否为 0~9 的数位
            } else if(str[i] == '.') { // 遇到小数点
                if(isDot || ise_or_E){
                    return false; // 小数点之前可以没有整数，但是不能重复出现小数点、或出现‘e’、'E'
                }

                isDot = true; // 标记已经遇到小数点
            } else if(str[i] == 'e' || str[i] == 'E') { // 遇到‘e’或'E'
                if(!isNum || ise_or_E){
                    return false; // ‘e’或'E'前面必须有整数，且前面不能重复出现‘e’或'E'
                }

                ise_or_E = true; // 标记已经遇到‘e’或'E'
                isNum = false; // 重置isNum，因为‘e’或'E'之后也必须接上整数，防止出现 123e或者123e+的非法情况
            } else if(str[i] == '-' ||str[i] == '+') {
                if(i!=0 && str[i-1] != 'e' && str[i-1] != 'E'){
                    return false; // 正负号只可能出现在第一个位置，或者出现在‘e’或'E'的后面一个位置
                }
            } else {
                return false; // 其它情况均为不合法字符
            }
        }

        return isNum;
    }
}
