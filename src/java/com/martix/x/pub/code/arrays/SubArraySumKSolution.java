package com.martix.x.pub.code.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew-Geng on 11:54 下午 2021/4/16
 * <p>
 * 和为K的子数组
 * lc 560
 * <p>
 * 给定一个整数数组和一个整数k，你需要找到该数组中和为k的连续的子数组的个数。
 * <p>
 * 示例 1 :
 * <p>
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 * <p>
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数k的范围是[-1e7, 1e7]。
 */
public class SubArraySumKSolution {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 2};
        System.out.println(new SubArraySumKSolution().subarraySum_2(nums, 2));
    }

    /**
     * 基于前缀和的原理：preSum[i]就是num[0...i-1]的和，那么如果要求num[i..j]，那么只需要操作 preSum[j+1]-preSum[i]
     * 时间复杂度O(N^2),空间复杂度O(N)
     * 性能较差，需要的是穷举
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum_1(int[] nums, int k) {
        int[] preSum = new int[nums.length + 1];

        preSum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        int result = 0;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (preSum[i] - preSum[j] == k) {
                    result++;
                }
            }
        }

        return result;
    }

    /**
     * 优化升级版： 直接记录下有几个sum[j]和sum[i]-k相等，直接更新结果，就可以避免内循环
     * <p>
     * 时间复杂度O(N)
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum_2(int[] nums, int k) {
        Map<Integer, Integer> preSum = new HashMap<>(); //前缀和，key：前缀和，value:该前缀和的次数
        preSum.put(0, 1);

        int sum_i = 0, result = 0;
        for (int i = 0; i < nums.length; i++) {
            sum_i = sum_i + nums[i];

            int sum_j = sum_i - k; //这里是要找的前缀和nums[0..j]
            if (preSum.containsKey(sum_j)) {
                result += preSum.get(sum_j);
            }

            preSum.put(sum_i, preSum.getOrDefault(sum_i, 0) + 1);
        }

        return result;
    }
}
