package com.martix.x.pub.stack;

import java.util.Stack;

/**
 * Created by Andrew-Geng on 1:38 下午 2021/5/16
 * 每日温度 lc 739
 * <p>
 * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * <p>
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 */
public class DailyTemperaturesSolution {

    public static void main(String[] args) {
        int[] num2 = new int[]{73, 74, 75, 71, 69, 72, 76, 73};

        int[] result = new DailyTemperaturesSolution().dailyTemperatures_1(num2);
        System.out.println(result);
    }

    /**
     * 还是单调栈的思路：
     * 但是栈中存放的是索引，不是值
     * <p>
     * 时间复杂度O(n)
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = temperatures.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }

            result[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }

        return result;
    }

    /**
     * 暴力解法
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures_1(int[] temperatures) {
        int[] result = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            int curVal = temperatures[i];

            int j = i + 1;
            while (j < temperatures.length && temperatures[j] <= curVal) {
                j++;
            }

            if (j == temperatures.length) {
                result[i] = 0;
            } else {
                result[i] = j - i;
            }
        }

        return result;
    }
}
