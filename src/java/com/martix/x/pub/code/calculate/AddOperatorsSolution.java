package com.martix.x.pub.code.calculate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew-Geng on 22:47 2022/9/14
 * 给表达式添加运算符 lc 282 hard
 * <p>
 * 给定一个仅包含数字0-9的字符串 num 和一个目标值整数 target ，在 num 的数字之间添加 二元 运算符（不是一元）+、-或*，返回 所有 能够得到 target 的表达式。
 * <p>
 * 注意，返回表达式中的操作数 不应该 包含前导零。
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: num = "123", target = 6
 * 输出: ["1+2+3", "1*2*3"]
 * 解释: “1*2*3” 和 “1+2+3” 的值都是6。
 * 示例2:
 * <p>
 * 输入: num = "232", target = 8
 * 输出: ["2*3+2", "2+3*2"]
 * 解释: “2*3+2” 和 “2+3*2” 的值都是8。
 * 示例 3:
 * <p>
 * 输入: num = "3456237490", target = 9191
 * 输出: []
 * 解释: 表达式 “3456237490” 无法得到 9191 。
 */
public class AddOperatorsSolution {

    String num;
    int target;
    int len;
    List<String> list;

    /**
     * 回溯
     *
     * 回溯出口，长度一致!如果结果也一致就加入数组否则直接回溯！
     * 每次选择有三个选择：+ - *
     *
     * @param num
     * @param target
     * @return
     */
    public List<String> addOperators(String num, int target) {
        this.num = num;
        this.target = target;
        len = num.length();
        list = new ArrayList<>();
        dfs(0, 0, 0, "");
        return list;
    }

    void dfs(int length, long prev, long current, String s) {
        if (length == len) {
            if (current == target) {
                list.add(s);
            }

            return;
        }

        for (int i = length; i < len; i++) {
            if (i != length && num.charAt(length) == '0') {
                break;
            }

            long number = Long.parseLong(num.substring(length, i + 1));

            if (length == 0) {
                //说明前面没有数字！
                dfs(i + 1, number, number, s + number);
            } else {
                //说明前面已经有数字！
                dfs(i + 1, number, current + number, s + "+" + number);

                dfs(i + 1, -number, current - number, s + "-" + number);

                long multi = prev * number;

                dfs(i + 1, multi, current - prev + multi, s + "*" + number);
            }
        }
    }

}
