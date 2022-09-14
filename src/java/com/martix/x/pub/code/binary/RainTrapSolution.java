package com.martix.x.pub.code.binary;

/**
 * Created by Andrew-Geng on 12:23 上午 2021/4/20
 * 接雨水 lc 42 hard
 * <p>
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * <p>
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 */
public class RainTrapSolution {

    public static void main(String[] args) {
        int[] i = new int[]{3,1,2,5,2,4};
        System.out.println(new RainTrapSolution().trap_1(i));
    }

    /**
     * water[i]=min(max(height[0..i]),max(height[i..n-1]))-height[i]，
     * 即表示第i个位置的雨水量，由i左边方格最高值，和 i右边方格最高值 的最小值决定，并最后减去i的方格高度
     * <p>
     * 1. 先用暴力解法处理
     * <p>
     * 时间复杂度O(N^2)，空间复杂度O(1)
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int result = 0;

        for (int i = 0; i < height.length - 1; i++) {
            int leftMax = 0, rightMax = 0;

            for (int j = i; j >= 0; j--) {
                leftMax = Math.max(leftMax, height[j]);  //j左边最高的柱子
            }
            for (int j = i; j < height.length; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }

            result += Math.min(leftMax, rightMax) - height[i];
        }

        return result;
    }

    /**
     * 备忘录优化
     * 通过增加两个数组，左右最大值的数组，降低遍历的次数，也是避免重复计算
     * 时间复杂度O（N),但是空间复杂度O（N）
     *
     * @param height
     * @return
     */
    public int trap_1(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int result = 0;
        int[] leftMax = new int[height.length], rightMax = new int[height.length];

        leftMax[0] = height[0];
        rightMax[height.length - 1] = height[height.length - 1];

        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }

        for (int i = height.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }

        for (int i = 0; i < height.length - 1; i++) {
            result += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return result;
    }

    /**
     *
     * water[i]=min(max(height[0..i]),max(height[i..n-1]))-height[i]，
     * 即表示第i个位置的雨水量，由i左边方格最高值，和 i右边方格最高值 的最小值决定，并最后减去i的方格高度
     * <p>
     * 借助双指针的思路，进一步降低空间复杂度 到 O(1)
     *
     * @param height
     * @return
     */
    public int trap_3(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int left = 0, right = height.length - 1;
        int result = 0;
        int leftMax = height[0], rightMax = height[height.length - 1];

        while (left <= right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if (leftMax < rightMax) {
                result += leftMax - height[left];
                left++;
            } else {
                result += rightMax - height[right];
                right--;
            }
        }

        return result;
    }
}
