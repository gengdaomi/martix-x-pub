package com.martix.x.pub.code.prefix;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew-Geng on 11:25 下午 2021/5/18
 * <p>
 * 连续的子数组和 lc 523
 * <p>
 * 给定一个包含 非负数 的数组和一个目标 整数k ，编写一个函数来判断该数组是否含有连续的子数组，
 * 其大小至少为 2，且总和为 k 的倍数，即总和为 n * k ，其中 n 也是一个整数。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[23,2,4,6,7], k = 6
 * 输出：True
 * 解释：[2,4] 是一个大小为 2 的子数组，并且和为 6。
 * 示例 2：
 * <p>
 * 输入：[23,2,6,4,7], k = 6
 * 输出：True
 * 解释：[23,2,6,4,7]是大小为 5 的子数组，并且和为 42。
 *  
 * <p>
 * 说明：
 * <p>
 * 数组的长度不会超过 10,000 。
 * 你可以认为所有数字总和在 32 位有符号整数范围内。
 */
public class CheckSubarraySumSolution {

    public static void main(String[] args) {
        int[] num = new int[]{1, 1};
        boolean f = new CheckSubarraySumSolution().checkSubarraySum(num, 1);
        System.out.println(f);
    }

    /**
     * 前缀和+hashmap
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> preSum = new HashMap<>(); //前缀和，key：sum%k，value:数组下标
        preSum.put(0, -1);

        int sum_i = 0;
        for (int i = 0; i < nums.length; i++) {
            sum_i = sum_i + nums[i];

            if (k != 0) { //k不为0 保障的是求余的时候 不会异常
                sum_i = sum_i % k;
            }

            if (preSum.containsKey(sum_i)) {
                if (i - preSum.get(sum_i) > 1) { //用于保障子数组至少长度大于1
                    return true;
                }
            } else {
                preSum.put(sum_i, i);
            }
        }

        return false;
    }

    /**
     * 前缀和的思路  由于数组可能过大，导致超时 ，不一定是最优解
     * <p>
     * 时间复杂度O(n^2) 空间复杂度O(n)
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum_1(int[] nums, int k) {
        int[] preSum = new int[nums.length + 1];
        preSum[0] = 0;

        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j < i; j++) {

                int sub = preSum[i + 1] - preSum[j];  //i+1的目的是由于题中要求子数组至少大于2
                if (sub == k || (k != 0 && sub % k == 0)) {
                    System.out.println(sub);
                    return true;
                }
            }
        }

        return false;
    }
}
