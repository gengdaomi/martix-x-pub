package com.martix.x.pub.code.validate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Andrew-Geng 2020/3/24-3:33 下午
 *
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 * 示例:
 *
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 *
 */
public class IpValidateSolution {
    private List<String> listResult;

    public List<String> restoreIpAddresses(String str) {
        if (stringNotPredicate.test(str)) {
            System.out.println("输入的数字字符串没有找到符合的Ip！");
            return new ArrayList<>();
        }

        listResult = new ArrayList<>();

        execute(str, 0, 0, "");

        return listResult;
    }

    /**
     * 遍历所有的可能的Ip地址
     *
     * @param numStr
     * @param index 记录的起始位置
     * @param subCount ip的一个字符数据长度
     * @param temp 中间值
     */
    private void execute(String numStr, int index, int subCount, String temp) {
        if (subCount == 3) {
            String t = numStr.substring(index);

            if (ipNumberPredicate.test(t)){//判断是否符合合法的IP地址规则
                //添加到结果中
                listResult.add(temp + t);
            }

            return;
        } else {
            // 每一次最长的截取长度是i，最短1位，最长3位
            for (int i = 1; i <= 3 && index + i < numStr.length(); i++) {

                String t = numStr.substring(index, index + i);
                if (ipNumberPredicate.test(t)){
                    execute(numStr, index + i, subCount + 1, temp + t + ".");
                }

            }
        }
    }

    /**
     * 判断当前字符串是否满足不能正常切分
     * true-不能正常切分
     * false-能够正常切分
     */
    private Predicate<String> stringNotPredicate = str -> (str == null || str.length() == 0 || str.length() < 4 || str.length() > 12);

    /**
     * 判断当前分割出来的数字组是否是符合0-255的（我这里没有做 类似123.33.33.002 去除掉00的操作）
     */
    private Predicate<String> ipNumberPredicate = num -> {
        if ((num.charAt(0) == '0' && num.length() > 1) || Integer.parseInt(num) >= 256) {
            return false;
        }

        return true;
    };

    public static void main(String[] args){
        String s = "25525511135";
        System.out.println(new IpValidateSolution().restoreIpAddresses(s));
    }
}
