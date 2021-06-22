package com.martix.x.pub.dymaic;

/**
 * Created by Andrew-Geng on 11:11 下午 2021/4/2
 * <p>
 * 目标和 lc 494
 * <p>
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号+和-。对于数组中的任意一个整数，你都可以从+或-中选择一个符号添加在前面。
 * <p>
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：nums: [1, 1, 1, 1, 1], S: 3
 * 输出：5
 * 解释：
 * <p>
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * <p>
 * 一共有5种方法让最终目标和为3。
 *  
 * <p>
 * 提示：
 * <p>
 * 数组非空，且长度不会超过 20 。
 * 初始的数组的和不会超过 1000 。
 * 保证返回的最终结果能被 32 位整数存下。
 */
public class FindTargetSumWaysSolution {

    int count = 0;

    /**
     * 递归的方式
     * <p>
     * 当我们处理到第 i 个数时，我们可以将它添加 + 或 -，递归地搜索这两种情况。当我们处理完所有的 N 个数时，我们计算出所有数的和，并判断是否等于 S
     * <p>
     * 时间复杂度：
     * O(2^N)，其中
     * N 是数组 nums 的长度。
     * 空间复杂度：
     * O(N)，为递归使用的栈空间大小。
     *
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays(int[] nums, int S) {
        calculate(nums, 0, 0, S);
        return count;
    }

    private void calculate(int[] nums, int i, int sum, int S) {
        if (i == nums.length) {

            if (sum == S) {
                count++;
            }
        } else {
            calculate(nums, i + 1, sum + nums[i], S);
            calculate(nums, i + 1, sum - nums[i], S);
        }
    }

    public static void main(String[] args) {
        int[] s = new int[]{1, 1, 1, 1, 1};
        System.out.println(new FindTargetSumWaysSolution().findTargetSumWays(s, 3));
    }
}
