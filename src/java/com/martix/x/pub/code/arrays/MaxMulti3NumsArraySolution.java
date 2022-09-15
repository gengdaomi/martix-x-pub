package com.martix.x.pub.code.arrays;

import java.util.Arrays;

/**
 * Created by Andrew-Geng on 13:30 2022/9/12
 * 三个数的最大乘积 lc 628
 * <p>
 * 给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：6
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,4]
 * 输出：24
 * 示例 3：
 * <p>
 * 输入：nums = [-1,-2,-3]
 * 输出：-6
 */
public class MaxMulti3NumsArraySolution {


    /**
     * 优化版 线性扫描
     * <p>
     * 在下面的方法中，实际求的是数组中最小的两个数以及最大的三个数，所以不需要排序，线性扫描找出这5个数
     * <p>
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE; //最小的 和第二小的
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE; //最大的，第二大 第三大

        for (int x : nums) {
            if (x < min1) {
                min2 = min1;
                min1 = x;
            } else if (x < min2) {
                min2 = x;
            }

            if (x > max1) {
                max3 = max2;
                max2 = max1;
                max1 = x;
            } else if (x > max2) {
                max3 = max2;
                max2 = x;
            } else if (x > max3) {
                max3 = x;
            }
        }

        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }


    /**
     * 核心 排序的思路
     * <p>
     * 首先将数组排序。
     * <p>
     * 1.如果数组中全是非负数，则排序后最大的三个数相乘即为最大乘积；如果全是非正数，则最大的三个数相乘同样也为最大乘积。
     * 2.如果数组中有正数有负数，则最大乘积既可能是三个最大正数的乘积，也可能是两个最小负数（即绝对值最大）与最大正数的乘积。
     * <p>
     * 综上，我们在给数组排序后，分别求出三个最大正数的乘积，以及两个最小负数与最大正数的乘积，二者之间的最大值即为所求答案。
     * <p>
     * 时间复杂度 O(nlogn),n为数组长度
     * 空间复杂度 O(logN)
     *
     * @param nums
     * @return
     */
    public int maximumProduct_1(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 3] * nums[n - 2] * nums[n - 1]);

    }


}
