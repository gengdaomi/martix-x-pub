package com.martix.x.pub.code.binary;

/**
 * Created by Andrew-Geng on 10:33 2022/12/17
 * 通过连接另一个数组的子数组得到一个数组 lc 1764
 *
 * 给你一个长度为 n的二维整数数组groups，同时给你一个整数数组nums。
 *
 * 你是否可以从 nums中选出 n个 不相交 的子数组，使得第 i个子数组与 groups[i]（下标从 0开始）完全相同，且如果i > 0，那么第(i-1)个子数组在 nums中出现的位置在第 i个子数组前面。
 * （也就是说，这些子数组在 nums中出现的顺序需要与 groups 顺序相同）
 *
 * 如果你可以找出这样的 n 个子数组，请你返回true ，否则返回false。
 *
 * 如果不存在下标为 k的元素 nums[k]属于不止一个子数组，就称这些子数组是 不相交 的。子数组指的是原数组中连续元素组成的一个序列。
 *
 *
 * 示例 1：
 *
 * 输入：groups = [[1,-1,-1],[3,-2,0]], nums = [1,-1,0,1,-1,-1,3,-2,0]
 * 输出：true
 * 解释：你可以分别在 nums 中选出第 0 个子数组 [1,-1,0,1,-1,-1,3,-2,0] 和第 1 个子数组 [1,-1,0,1,-1,-1,3,-2,0] 。
 * 这两个子数组是不相交的，因为它们没有任何共同的元素。
 * 示例 2：
 *
 * 输入：groups = [[10,-2],[1,2,3,4]], nums = [1,2,3,4,10,-2]
 * 输出：false
 * 解释：选择子数组 [1,2,3,4,10,-2] 和 [1,2,3,4,10,-2] 是不正确的，因为它们出现的顺序与 groups 中顺序不同。
 * [10,-2] 必须出现在 [1,2,3,4] 之前。
 * 示例 3：
 *
 * 输入：groups = [[1,2,3],[3,4]], nums = [7,7,1,2,3,4,7,7]
 * 输出：false
 * 解释：选择子数组 [7,7,1,2,3,4,7,7] 和 [7,7,1,2,3,4,7,7] 是不正确的，因为它们不是不相交子数组。
 * 它们有一个共同的元素 nums[4] （下标从 0 开始）。
 *
 */
public class ConnectSubArraySolution {

    /**
     * 方法一：贪心 + 双指针
     *
     * @param groups
     * @param nums
     * @return
     */
    public boolean canChoose(int[][] groups, int[] nums) {
        int i = 0;
        for (int k = 0; k < nums.length && i < groups.length;) {
            if (check(groups[i], nums, k)) {
                k += groups[i].length;
                i++;
            } else {
                k++;
            }
        }

        return i == groups.length;
    }

    public boolean check(int[] g, int[] nums, int k) {
        if (k + g.length > nums.length) {
            return false;
        }

        for (int j = 0; j < g.length; j++) {
            if (g[j] != nums[k + j]) {
                return false;
            }
        }

        return true;
    }

    /**
     * 解法一：双指针
     *
     * 利用双指针从前往后模式匹配即可，由于子数组必须连续，当某一位置无法匹配时，
     * 需要将nums数组的指针移动到最开始匹配的下一个数字继续重新匹配。
     *
     * 时间复杂度：O(nm),其中n为nums长度，m为最大的group[i]长度
     * 空间复杂度：O(1)
     *
     * @param groups
     * @param nums
     * @return
     */
    public boolean canChoose_1(int[][] groups, int[] nums) {
        int p1 = 0, p2, n = nums.length;

        for (int[] g : groups) {
            p2 = 0;

            while (p2 < g.length && p1 < n) {
                if (nums[p1++] == g[p2]){
                    p2++;
                } else {
                    p1 -= p2; p2 = 0;
                } //回到开头的下一个数字重新开始
            }

            if (p1 >= n && p2 != g.length){
                return false;
            }
        }

        return true;
    }

}
