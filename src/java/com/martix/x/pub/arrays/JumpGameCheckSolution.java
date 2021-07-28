package com.martix.x.pub.arrays;

/**
 * Created by Andrew-Geng on 9:30 下午 2021/7/22
 * 跳跃游戏 lc 55
 * <p>
 * 给定一个非负整数数组nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 */
public class JumpGameCheckSolution {

    /**
     * 贪心
     * <p>
     * 核心思路：
     * <p>
     * 只要存在一个位置x，它本身可以到达，并且它跳跃的最大长度为x+nums[x]，
     * 这个值大于等于y，即x+nums[x]≥y，那么位置y 也可以到达。
     * <p>
     * 换句话说，对于每一个可以到达的位置x，它使得x+1,x+2,⋯,x+nums[x] 这些连续的位置都可以到达。
     * 这样以来，我们依次遍历数组中的每一个位置，并实时维护 最远可以到达的位置。
     * <p>
     * 对于当前遍历到的位置x，如果它在最远可以到达的位置 的范围内，
     * 那么我们就可以从起点通过若干次跳跃到达该位置，因此我们可以用x+nums[x] 更新 最远可以到达的位置。
     *
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;

        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {

                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }

        return false;
    }
}
