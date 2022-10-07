package com.martix.x.pub.code.dymaic;

/**
 * Created by Andrew-Geng on 10:58 下午 2021/5/19
 * <p>
 * 乘积最大子数组 lc 152
 * <p>
 * 给你一个整数数组 nums，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释:子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * <p>
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释:结果不能为 2, 因为 [-2,-1] 不是子数组。
 */
public class MaxMultiSubArraySolution {

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, -1, 2, 3, -5, -2};
        System.out.println(new MaxMultiSubArraySolution().maxProduct(nums));
    }

    /**
     * 动态规划的思路：
     * 令imax为当前最大值，则当前最大值为 imax = max(imax * nums[i], nums[i])
     * <p>
     * 由于存在负数，那么会导致最大的变最小的，最小的变最大的。因此还需要维护当前最小值imin，imin = min(imin * nums[i], nums[i])
     * <p>
     * 当负数出现时则imax与imin进行交换再进行下一步计算
     * <p>
     * 时间复杂度：
     * O(n)
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {

        int result = Integer.MIN_VALUE, imax = 1, imin = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                imax ^= imin;
                imin ^= imax;
                imax ^= imin;
            }

            imax = Math.max(imax * nums[i], nums[i]);
            imin = Math.min(imin * nums[i], nums[i]);

            result = Math.max(result, imax);
        }

        return result;
    }
}
