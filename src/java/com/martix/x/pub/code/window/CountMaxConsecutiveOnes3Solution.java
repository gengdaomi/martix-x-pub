package com.martix.x.pub.code.window;

/**
 * Created by Andrew-Geng on 00:29 2022/10/8
 * 最大连续1的个数 III lc1004
 * <p>
 * 给定一个二进制数组nums和一个整数 k，如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释：[1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 * 示例 2：
 * <p>
 * 输入：nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * 输出：10
 * 解释：[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 */
public class CountMaxConsecutiveOnes3Solution {

    /**
     * 滑动窗口
     * <p>
     * 题意转换下：
     * 把「最多可以把 K 个 0 变成 1，求仅包含 1 的最长子数组的长度」转换为 「找出一个最长的子数组，该子数组内最多允许有 K 个 0 」。
     * 所以 本题是求最大连续子区间，可以使用滑动窗口方法。滑动窗口的限制条件是：窗口内最多有 K 个 0。
     * <p>
     * 核心思路：
     * 1.使用 left 和  right 两个指针，分别指向滑动窗口的左右边界。
     * 2.right 主动右移： right 指针每次移动一步。当 A[right] 为 0，说明滑动窗口内增加了一个0；
     * 3.left 被动右移：判断此时窗口内0 的个数，如果超过了K，则 left 指针被迫右移，直至窗口内的0 的个数小于等于 K 为止。
     * 4.滑动窗口长度的最大值就是所求
     * <p>
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @param nums
     * @param k
     * @return
     */
    public int longestOnes(int[] nums, int k) {
        int N = nums.length;
        int result = 0;
        int left = 0, right = 0;
        int zeros = 0;

        while (right < N) {
            if (nums[right] == 0)
                zeros++;

            while (zeros > k) {
                if (nums[left++] == 0)
                    zeros--;
            }

            result = Math.max(result, right - left + 1);
            right++;
        }
        return result;
    }

    /**
     * 二分查找
     * 核心思路：
     *
     * 对于数组nums的区间[left,right],，只要它包含不超过k 个0，我们就可以根据它构造出一段满足要求，并且长度为right−left+1 的区间。
     *
     * 该问题进行如下的转化，即：
     *
     * 对于任意的右端点right，希望找到最小的左端点left，
     * 使得  [left,right] 包含不超过k 个  0。
     * 只要我们枚举所有可能的右端点，将得到的区间的长度取最大值，即可得到答案。
     *
     * 要想快速判断一个区间内0 的个数，我们可以考虑将数组A 中的0 变成1，1 变成  0。
     * 此时，我们对数组A 求出前缀和，记为数组prefixArr，那么 [left,right] 中包含不超过 k 个1（注意这里就不是0 了
     *
     *
     * 时间复杂度O(nlogn) 其中n为数组nums的长度；每一次二分查找的时间复杂度为O(logn),我们需要枚举right进行n次二分查找，所以总的复杂度O(nlogn)
     * 空间复杂度O(n) 前缀和数组prefixArr的长度;
     *
     * @param nums
     * @param k
     * @return
     */
    public int longestOnes_1(int[] nums, int k) {
        int n = nums.length;
        int[] prefixArr = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            prefixArr[i] = prefixArr[i - 1] + (1 - nums[i - 1]);
        }

        int result = 0;
        for (int right = 0; right < n; right++) {
            int left = binarySearch(prefixArr, prefixArr[right + 1] - k);
            result = Math.max(result, right - left + 1);
        }

        return result;
    }

    private int binarySearch(int[] prefixArr, int target) {
        int low = 0, high = prefixArr.length - 1;

        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (prefixArr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

}
