package com.martix.x.pub.code.greedy;

/**
 * Created by Andrew-Geng on 9:35 下午 2021/7/22
 *
 * 跳跃游戏 II
 *
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 假设你总是可以到达数组的最后一个位置。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 示例 2:
 *
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 */
public class JumpGameLestJumpSolution {

    /**
     * 正向查找可到达的最大位置
     *
     * 如果我们「贪心」地进行正向查找，每次找到可到达的最远位置，就可以在线性时间内得到最少的跳跃次数。
     * 例如，对于数组 [2,3,1,2,4,2,3]，初始位置是下标 0，从下标 0 出发，最远可到达下标 2,
     * 下标 0 可到达的位置中，下标 1 的值是 3，从下标 1 出发可以达到更远的位置，因此第一步到达下标 1。
     *
     * 在遍历数组时，我们不访问最后一个元素，这是因为在访问最后一个元素之前，我们的边界一定大于等于最后一个位置，
     * 否则就无法跳到最后一个位置了。如果访问最后一个元素，
     * 在边界正好为最后一个位置的情况下，我们会增加一次「不必要的跳跃次数」，因此我们不必访问最后一个元素。
     *
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;

        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);

            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }

        return steps;
    }

    /**
     * 反向查找出发位置
     *
     * 我们的目标是到达数组的最后一个位置，因此我们可以考虑最后一步跳跃前所在的位置，该位置通过跳跃能够到达最后一个位置。
     *
     * 如果有多个位置通过跳跃都能够到达最后一个位置:
     * 直观上来看，我们可以「贪心」地选择距离最后一个位置最远的那个位置，也就是对应下标最小的那个位置。
     * 因此，我们可以从左到右遍历数组，选择第一个满足要求的位置。
     *
     * 找到最后一步跳跃前所在的位置之后，我们继续贪心地寻找倒数第二步跳跃前所在的位置，以此类推，直到找到数组的开始位置。
     *
     * 时间复杂度O(n^2)
     * 空间复杂度O(1)
     * @param nums
     * @return
     */
    public int jump_1(int[] nums) {
        int position = nums.length - 1;
        int steps = 0;

        while (position > 0) {

            for (int i = 0; i < position; i++) {

                if (i + nums[i] >= position) {
                    position = i;
                    steps++;
                    break;
                }
            }
        }

        return steps;
    }

}
