package com.martix.x.pub.code.calculate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew-Geng on 22:31 2022/9/14
 * 为运算表达式设计优先级 lc 241
 *
 * 给你一个由数字和运算符组成的字符串expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。
 * 你可以 按任意顺序 返回答案。
 *
 * 生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 104 。
 *
 *
 * 示例 1：
 *
 * 输入：expression = "2-1-1"
 * 输出：[0,2]
 * 解释：
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * 示例 2：
 *
 * 输入：expression = "2*3-4*5"
 * 输出：[-34,-14,-10,-10,10]
 * 解释：
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 */
public class DiffWays2ComputeSolution {

    /**
     * 递归
     *
     * 把字符串按照操作符分割为两个子表达式，然后将两个子表达式的结果集进行当前op的操作，
     * 组装成新的结果集，对每个op分别进行分割得到的结果集之和就是最终的答案。
     * 而子表达式又是相同的问题，可以采用递归进行计算，最终会递归到一个digit上。
     *
     * @param expression
     * @return
     */
    public List<Integer> diffWaysToCompute(String expression) {
        if (expression == null || expression.length() == 0) {//表达式为空
            return new ArrayList<>();
        }

        char[] chars = expression.toCharArray();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];

            if (!Character.isDigit(aChar)) {//如果当前字符是操作符，也就是op，进行分割
                //递归拿到左右两个表达式的结果集
                List<Integer> leftList = diffWaysToCompute(expression.substring(0, i));
                List<Integer> rightList = diffWaysToCompute(expression.substring(i + 1));


                for (Integer left : leftList) {//对两个结果集的所有结果进行op运算

                    for (Integer right : rightList) {
                        if (aChar == '+') {
                            result.add(left + right);
                        } else if (aChar == '-') {
                            result.add(left - right);
                        } else {
                            result.add(left * right);
                        }
                    }
                }
            }
        }

        //结果集是空，证明该字符串是数字，将数字加入结果集
        if (result.isEmpty()) {
            result.add(Integer.valueOf(expression));
        }

        return result;
    }


    /**
     * 动态规划
     *
     * 因为最终的答案是由一个个子问题（子表达式）的答案所构成，所以我们可以采用动态规划，将问题划分为一个个子问题来求解。
     * 首先我们将字符串分成digit、op、digit、op、digit、op、digit.....这样的序列,并且可知序列的长度是奇数个,所以子问题的最小长度为3（长度为1的digit不需要计算）,
     * 也就是一个op运算需要至少三个元素（两个digit和一个op），下一个子问题的长度为当前子问题+2（加一个op和一个digit）,
     * 所以我们可以从最小长度为3的子问题一步步求解最大长度的解；
     *
     * 时间复杂度  空间复杂度 O(2^n)  n为ops大小
     * @param expression
     * @return
     */
    public List<Integer> diffWaysToCompute_1(String expression){
        List<Object> ops = new ArrayList<>();

        for (int i = 0; i < expression.length(); ) {//将字符串分割为digit、op、digit、op、digit......这样的序列
            if (!Character.isDigit(expression.charAt(i))) {
                ops.add(expression.charAt(i));//添加操作符
                i++;
            } else {
                int digit = 0;//添加数字
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    digit = digit * 10 + expression.charAt(i) - '0';
                    i++;
                }

                ops.add(digit);
            }
        }

        List<Integer>[][] dp = new List[ops.size()][ops.size()];//dp[i][j]表示从i到j子问题（子表达式）的所有答案
        for (int i = 0; i < ops.size(); i++) {
            for (int j = 0; j < ops.size(); j++) {
                dp[i][j] = new ArrayList<>();
            }
        }

        //初始时，所有的digit都是自己本身并且数字都是隔着存放的，并且位置固定在偶数位(0,2,4...) 所以+2
        //eg：digit、op、digit、op、digit......
        for (int i = 0; i < ops.size(); i += 2) {
            dp[i][i].add((int) ops.get(i));
        }

        for (int len = 3; len <= ops.size(); len += 2) {//从长度为3的子问题开始计算

            for (int left = 0; left + len <= ops.size(); left += 2) {//左边界从0开始

                int right = left + len - 1;//右边界

                for (int k = left + 1; k < right; k += 2) {//按照op进行分割左右两个子表达式 +2表示下一个op
                    List<Integer> leftArr = dp[left][k - 1];
                    List<Integer> rightArr = dp[k + 1][right];

                    for (int num1 : leftArr) {//对左右两个子表达式的结果集进行合并处理
                        for (int num2 : rightArr) {
                            char op = (char) ops.get(k);
                            if (op == '+') {
                                dp[left][right].add(num1 + num2);
                            } else if (op == '-') {
                                dp[left][right].add(num1 - num2);
                            } else if (op == '*') {
                                dp[left][right].add(num1 * num2);
                            }
                        }
                    }
                }
            }
        }

        return dp[0][ops.size() - 1];
    }
}
