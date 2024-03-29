package com.martix.x.pub.code.calculate;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by Andrew-Geng on 21:53 2022/9/14
 * 基本计算器 lc224 hard
 *
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 *
 * 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 *
 * 示例 1：
 *
 * 输入：s = "1 + 1"
 * 输出：2
 * 示例 2：
 *
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 * 示例 3：
 *
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 *
 * 1 <= s.length <= 3* 105
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 * '+' 不能用作一元运算(例如， "+1"和 "+(2 + 3)"无效)
 * '-' 可以用作一元运算(即 "-1"和 "-(2 + 3)"是有效的)
 * 输入中不存在两个连续的操作符
 * 每个数字和运行的计算将适合于一个有符号的 32位 整数
 *
 */
public class BasicCalculator1Solution {

    /**
     * 核心思路：括号展开 + 栈
     *
     * 由于字符串除了数字与括号外，只有加号和减号两种运算符。
     * 因此，如果展开表达式中所有的括号，则得到的新表达式中，数字本身不会发生变化，只是每个数字前面的符号会发生变化；
     * 我们考虑使用一个取值为{−1,+1} 的整数sign代表当前的符号：
     * 1.与字符串中当前位置的运算符有关；
     * 2.如果当前位置处于一系列括号之内，则也与这些括号前面的运算符有关：每当遇到一个以 − 号开头的括号，则意味着此后的符号都要被「翻转」。
     *
     * 考虑到第二点，我们需要维护一个栈ops，其中栈顶元素记录了当前位置所处的每个括号所「共同形成」的符号。例如，对于字符串1+2+(3-(4+5))
     * 1.扫描到1+2时，由于当前位置没有被任何括号所包含，则栈顶元素为初始值+1；
     * 2.扫描到1+2+(3时，当前位置被一个括号所包含，该括号前面的符号为+ 号，因此栈顶元素依然+1；
     * 3.扫描到1+2+(3-(4 时，当前位置被两个括号所包含，分别对应着 + 号和 − 号，由于 + 号和  − 号合并的结果为− 号，因此栈顶元素变为−1；
     *
     * 在得到栈ops之后，sign的取值就能够确定了：如果当前遇到 + 号，则更新sign<-ops.top; 如果当前遇到 - 号，则更新sign<- -ops.top;
     * 然后，每当遇到( 时，都要将当前的sign 取值压入栈中；每当遇到) 时，都从栈中弹出一个元素。这样，我们能够在扫描字符串的时候，即时地更新ops 中的元素.
     *
     * 时间复杂度O(n) 空间复杂度O(n)
     * @param s
     * @return
     */
    public int calculate(String s) {
        Deque<Integer> ops = new LinkedList<Integer>();
        ops.push(1);
        int sign = 1;

        int result = 0;
        int n = s.length();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                sign = ops.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -ops.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                ops.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                ops.pop();
                i++;
            } else {
                long num = 0;

                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }

                result += sign * num;
            }
        }

        return result;
    }
}
