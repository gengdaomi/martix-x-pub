package com.martix.x.pub.code.dymaic;

/**
 * Created by Andrew-Geng on 10:22 下午 2021/7/19
 * <p>
 * 打家劫舍 II lc 213
 * <p>
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，
 * 这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 * <p>
 * 示例1：
 * <p>
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *     偷窃到的最高金额 = 1 + 3 = 4 。
 */
public class HouseRobberCycleSolution {

    /**
     * 动态规划
     * <p>
     * 首先考虑最简单的情况。如果只有一间房屋，则偷窃该房屋，可以偷窃到最高总金额。
     * 如果只有两间房屋，则由于两间房屋相邻，不能同时偷窃，只能偷窃其中的一间房屋，因此选择其中金额较高的房屋进行偷窃，可以偷窃到最高总金额。
     * <p>
     * 因为头尾相连，所以 第一间房屋和最后一间房屋不能同时偷窃；
     * <p>
     * 1.假设数组nums 的长度为n。如果不偷窃最后一间房屋，则偷窃房屋的下标范围是[0,n−2]；
     * 2.如果不偷窃第一间房屋，则偷窃房屋的下标范围是[1,n−1]
     * <p>
     * 用dp[i]表示前i 间房屋能偷窃到的最高总金额，那么就有如下的状态转移方程:
     * dp[i]=max(dp[i−2]+nums[i],dp[i−1]);
     * <p>
     * 边界条件:
     * dp[start]=num[start],只有一间房屋，则偷窃该房屋
     * dp[start+1]=max(num[start],num[start+1]) 只有两间房，选择其中金额较高的房屋进行偷窃；
     * <p>
     * 计算得到dp[end] 即为下标范围[start,end] 内可以偷窃到的最高总金额。
     * 分别取(start,end)=(0,n−2) 和(start,end)=(1,n−1) 进行计算，取两个dp[end] 中的最大值，即可得到最终结果；
     *
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int length = nums.length;

        if (length == 1) {
            return nums[0];
        } else if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        return Math.max(robRange(nums, 0, length - 2), robRange(nums, 1, length - 1));
    }

    private int robRange(int[] nums, int start, int end) {
        int first = nums[start];
        int second = Math.max(nums[start], nums[start + 1]);

        for (int i = start + 2; i <= end; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

}
