package com.martix.x.pub.code.arrays;

/**
 * Created by Andrew-Geng on 00:19 2022/10/8
 *
 * 最大连续 1 的个数 lc 485 easy
 *
 * 给定一个二进制数组 nums ， 计算其中最大连续 1 的个数。
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,0,1,1,1]
 * 输出：3
 * 解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
 *
 *  示例 2:
 *
 * 输入：nums = [1,0,1,1,0,1]
 * 输出：2
 *
 */
public class CountMaxConsecutiveOnes1Solution {

    public static void main(String[] args){
        int[] arr = {1,1,0,1,1,1};
        int result = new CountMaxConsecutiveOnes1Solution().findMaxConsecutiveOnes(arr);
        System.out.println(result);
    }

    /**
     * 一次遍历
     *
     * 为了得到数组中最大连续1 的个数，需要遍历数组，并记录最大的连续1 的个数和当前的连续1 的个数。
     * 如果当前元素是1，则将当前的连续1 的个数加1，否则，使用之前的连续1 的个数更新最大的连续1 的个数，
     * 并将当前的连续1 的个数清零。
     *
     * 遍历数组结束之后，需要再次使用当前的连续1 的个数更新最大的连续 1 的个数，
     * 因为数组的最后一个元素可能是1，且最长连续1 的子数组可能出现在数组的末尾，
     * 如果遍历数组结束之后不更新最大的连续1 的个数，则会导致结果错误；
     *
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0, count = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                maxCount = Math.max(maxCount, count);
                count = 0;
            }
        }

        maxCount = Math.max(maxCount, count);
        return maxCount;
    }
}
