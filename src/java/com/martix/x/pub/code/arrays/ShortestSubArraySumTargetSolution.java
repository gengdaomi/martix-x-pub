package com.martix.x.pub.code.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew-Geng on 3:51 下午 2021/4/28
 * <p>
 * 长度最小的子数组
 * lc 209
 * <p>
 * 给定一个含有n个正整数的数组和一个正整数 target 。
 * <p>
 * 找出该数组中存在一个子数组满足其和 ≥ target， 长度最小的连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。
 * 如果不存在符合条件的子数组，返回 0 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组[4,3]是该条件下的长度最小的子数组。
 * 示例 2：
 * <p>
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 */
public class ShortestSubArraySumTargetSolution {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 1, 2, 4, 3};
        System.out.println(new ShortestSubArraySumTargetSolution().minSubArrayLen(7, arr));
    }

    /**
     * 滑动窗口算法，简化版的
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int result = Integer.MAX_VALUE;
        int start = 0, end = 0;

        int sum = 0;
        while (end < nums.length) {
            sum += nums[end];

            while (sum >= target) {
                result = Math.min(result, end - start + 1); //加1 才能表示符合的长度， 比如当只有一个数字符合时，真实长度是1

                sum -= nums[start];
                start++;
            }

            end++;
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }

    /**
     * 滑动窗口算法
     * <p>
     * 时间复杂度O(n) , 空间复杂度O(1)
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen_0(int target, int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int result = nums.length + 1, sum = 0;
        int start = 0, end = -1;

        List<Integer> list = new ArrayList<>();
        while (start < nums.length) {
            if (end + 1 < nums.length && sum < target) {
                end++;
                sum += nums[end];
            } else {
                sum -= nums[start];
                start++;
            }

            if (sum >= target) {
                list.add(end - start + 1);
                result = Math.min(result, end - start + 1);
            }
        }

        System.out.println(list);
        return result == nums.length + 1 ? 0 : result;
    }

    /**
     * 暴力解法
     * O(n^2)
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen_2(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum >= s) {
                    ans = Math.min(ans, j - i + 1);
                    break;
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
