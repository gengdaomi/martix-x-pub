package com.martix.x.pub.arrays;

import java.util.Arrays;

/**
 * Created by Andrew-Geng on 1:05 上午 2021/6/9
 * <p>
 * 有序转化数组 lc 360
 * <p>
 * 给你一个已经 排好序 的整数数组 nums 和整数 a、b、c。对于数组中的每一个数 x，计算函数值 f(x) = ax2 + bx + c，请将函数值产生的数组返回。
 * 要注意，返回的这个数组必须按照 升序排列，并且我们所期望的解法时间复杂度为 O(n)。
 * <p>
 * 示例 1：
 * 输入: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
 * 输出: [3,9,15,33]
 * <p>
 * 示例 2：
 * 输入: nums = [-4,-2,2,4], a = -1, b = 3, c = 5
 * 输出: [-23,-5,1,7]
 */
public class SortTransformedArraySolution {

    public static void main(String[] args) {
    }

    /**
     * 核心：
     * 主要是要分别 判断 a是否为0，b是否大于0 或者小于0
     *
     *做 a b c 分别的临界判断
     * @param nums
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int[] result = new int[nums.length];
        int index;

        if (a == 0) {
            if (b > 0) {
                index = 0;

                for (int num : nums) {
                    result[index++] = this.fun(num, a, b, c);
                }
            } else if (b < 0) {
                index = nums.length - 1;

                for (int num : nums) {
                    result[index--] = this.fun(num, a, b, c);
                }
            } else { //b==0
                Arrays.fill(result, c);
            }

            return result;
        }

        //a!=0

        double midVal = -b * (1.0 / (2 * a));   // -b/2a 是抛物线的最值 （中间值）
        int left = 0;
        int right = nums.length - 1;

        if (a > 0) {    // 开口向上，有最小值
            index = nums.length - 1;    // 从最大值往下装入 result 中

            while (left <= right) {
                // x 距离中心横坐标越远的，y 越大
                if (Math.abs(midVal - nums[left]) > Math.abs(midVal - nums[right])) {
                    result[index] = this.fun(nums[left], a, b, c);
                    left++;
                } else {
                    result[index] = this.fun(nums[right], a, b, c);
                    right--;
                }
                index--;
            }
        } else {    // 开口向下，有最大值
            index = 0;

            while (left <= right) {
                // x 距离中心横坐标越远的，y 越小
                if (Math.abs(midVal - nums[left]) > Math.abs(midVal - nums[right])) {
                    result[index] = this.fun(nums[left], a, b, c);
                    left++;
                } else {
                    result[index] = this.fun(nums[right], a, b, c);
                    right--;
                }

                index++;
            }
        }

        return result;

    }

    /**
     * ax² + bx + c
     *
     * @param x
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int fun(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }
}
