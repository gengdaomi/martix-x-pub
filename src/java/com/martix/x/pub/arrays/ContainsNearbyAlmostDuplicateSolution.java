package com.martix.x.pub.arrays;

import java.util.TreeSet;

/**
 * Created by Andrew-Geng on 10:31 下午 2021/6/26
 * 存在重复元素 III
 * lc220
 *
 * 给你一个整数数组 nums 和两个整数k 和 t 。
 * 请你判断是否存在 两个不同下标 i 和 j，使得abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 *
 * 如果存在则返回 true，不存在返回 false。
 *
 *
 * 示例1：
 *
 * 输入：nums = [1,2,3,1], k = 3, t = 0
 * 输出：true
 * 示例 2：
 *
 * 输入：nums = [1,0,1,1], k = 1, t = 2
 * 输出：true
 * 示例 3：
 *
 * 输入：nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出：false
 *
 */
public class ContainsNearbyAlmostDuplicateSolution {

    /**
     * 滑动窗口 + 有序集合
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        TreeSet<Long> set = new TreeSet<Long>();

        for (int i = 0; i < n; i++) {
            //方法返回在这个集合中大于或者等于给定元素的最小元素，如果不存在这样的元素,返回null
            Long ceiling = set.ceiling((long) nums[i] - (long) t);

            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) {
                return true;
            }

            set.add((long) nums[i]);

            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }

        return false;
    }
}
