package com.martix.x.pub.window;

/**
 * Created by Andrew-Geng on 12:33 上午 2021/5/19
 * 乘积小于K的子数组 lc 713
 * <p>
 * 给定一个正整数数组nums。
 * <p>
 * 找出该数组内乘积小于k的 连续的 子数组的个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [10,5,2,6], k = 100
 * 输出: 8
 * 解释: 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
 * 说明:
 * <p>
 * 0 < nums.length <= 50000
 * 0 < nums[i] < 1000
 * 0 <= k < 10^6
 */
public class SubarrayMultiLessThanKSolution {

    public static void main(String[] args) {
        int[] num = new int[]{10, 9, 10, 4, 3, 8, 3, 3, 6, 2, 10, 10, 9, 3};
        int result = new SubarrayMultiLessThanKSolution().numSubarrayProductLessThanK(num, 19);
        System.out.println(result);
    }

    /**
     * 核心思路：
     * 滑动窗口 - 向右滑
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }

        int preSum = 1, result = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            preSum = preSum * nums[right];

            while (preSum >= k) {
                preSum /= nums[left];
                left++;
            }

            result = result + right - left + 1;
        }

        return result;
    }

}
