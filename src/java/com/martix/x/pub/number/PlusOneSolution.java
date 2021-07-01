package com.martix.x.pub.number;

import java.util.Arrays;

/**
 * Created by Andrew-Geng on 1:26 上午 2021/5/2
 * 加一
 * lc 66
 * <p>
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * <p>
 * 示例1：
 * <p>
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * 示例2：
 * <p>
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 * 示例 3：
 * <p>
 * 输入：digits = [0]
 * 输出：[1]
 */
public class PlusOneSolution {

    public static void main(String[] args) {
        int[] arr = new int[]{9, 9, 9, 9};
        int[] result = new PlusOneSolution().plusOne(arr);
        System.out.println(result);
    }

    public int[] plusOne(int[] digits) {
        boolean isOutArr = false; //判断是否会溢出原有数组，比如 9999 +1后，原数组长度由4位变为5位

        for (int i = digits.length - 1; i >= 0; i--) {
            int temp = digits[i] + 1;

            if (temp == 10) {
                digits[i] = 0;
                isOutArr = true;
                continue;
            } else {
                digits[i] = temp;
                isOutArr = false;
                break;
            }
        }

        if (isOutArr) { //增加1位
            int[] result = new int[digits.length + 1];
            Arrays.fill(result, 0);
            result[0] = 1;

            return result;
        } else {
            return digits;
        }
    }
}
