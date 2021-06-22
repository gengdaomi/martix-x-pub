package com.martix.x.count;

/**
 * Created by Andrew-Geng on 11:00 下午 2021/6/13
 * <p>
 * 盛最多水的容器 lc 11
 * <p>
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水
 * <p>
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * 示例 2：
 * <p>
 * 输入：height = [1,1]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：height = [4,3,2,1,4]
 * 输出：16
 * 示例 4：
 * <p>
 * 输入：height = [1,2,1]
 * 输出：2
 *  
 * <p>
 * 提示：public int maxArea(int[] height) {
 * <p>
 * }
 * <p>
 * n = height.length
 * 2 <= n <= 3 * 104
 * 0 <= height[i] <= 3 * 104
 */
public class RainMaxAreaSolution {

    /**
     * 双指针代表的是 可以作为容器边界的所有位置的范围。在一开始，双指针指向数组的左右边界，
     * 表示 数组中所有的位置都可以作为容器的边界，因为我们还没有进行过任何尝试。在这之后，
     * 我们每次将 对应的数字较小的那个指针 往 另一个指针 的方向移动一个位置，就表示我们认为 这个指针不可能再作为容器的边界了
     * <p>
     * 求出当前双指针对应的容器的容量；
     * 对应数字较小的那个指针以后不可能作为容器的边界了，将其丢弃，并移动对应的指针
     * <p>
     * 时间复杂度 O(N) 最多遍历一遍数组
     * 空间复杂度 O(1) 只需要额外的常数级别的空间
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int result = 0;

        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            result = Math.max(result, area);

            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return result;
    }
}
