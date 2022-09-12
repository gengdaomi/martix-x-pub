package com.martix.x.pub.code.arrays;

/**
 * Created by Andrew-Geng on 1:35 上午 2021/5/19
 * 寻找数组的中心下标 lc 724
 * <p>
 * 给你一个整数数组nums，请编写一个能够返回数组 “中心下标” 的方法。
 * 数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
 * 如果数组不存在中心下标，返回 -1 。如果数组有多个中心下标，应该返回最靠近左边的那一个。
 * <p>
 * 注意：中心下标可能出现在数组的两端。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1, 7, 3, 6, 5, 6]
 * 输出：3
 * 解释：
 * 中心下标是 3 。
 * 左侧数之和 (1 + 7 + 3 = 11)，
 * 右侧数之和 (5 + 6 = 11) ，二者相等。
 * 示例 2：
 * <p>
 * 输入：nums = [1, 2, 3]
 * 输出：-1
 * 解释：
 * 数组中不存在满足此条件的中心下标。
 * 示例 3：
 * <p>
 * 输入：nums = [2, 1, -1]
 * 输出：0
 * 解释：
 * 中心下标是 0 。
 * 下标 0 左侧不存在元素，视作和为 0 ；
 * 右侧数之和为 1 + (-1) = 0 ，二者相等。
 * <p>
 * 提示：
 * <p>
 * nums 的长度范围为[0, 10000]。
 * 任何一个nums[i] 将会是一个范围在[-1000, 1000]的整数。
 */
public class PivotIndexSolution {

    public static void main(String[] args) {
        int[] num = new int[]{1, 2, 3};
        System.out.println(new PivotIndexSolution().pivotIndex(num));
    }

    /**
     * 双指针的方式
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }

        for (int i = 0; i < nums.length; i++) {
            int left = 0, right = nums.length - 1;
            int leftSum = 0, rightSum = 0;

            while (left < i) {
                leftSum += nums[left++];
            }

            while (right > i) {
                rightSum += nums[right--];
            }

            if (leftSum == rightSum) {
                return i;
            }
        }

        return -1;
    }
}
