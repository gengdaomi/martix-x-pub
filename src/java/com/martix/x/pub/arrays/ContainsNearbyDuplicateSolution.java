package com.martix.x.pub.arrays;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Andrew-Geng on 10:27 下午 2021/6/26
 *
 * 存在重复元素 II
 * lc 219
 *
 * 给定一个整数数组和一个整数k，判断数组中是否存在两个不同的索引i和j，使得nums [i] = nums [j]，并且 i 和 j的差的 绝对值 至多为 k。
 *
 * 示例1:
 *
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 * 示例 2:
 *
 * 输入: nums = [1,0,1,1], k = 1
 * 输出: true
 * 示例 3:
 *
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 */
public class ContainsNearbyDuplicateSolution {

    /**
     * hash 表的方式
     *
     * 时间复杂度O(n)
     * 空间复杂度O(min(n,k))
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; ++i) {
            if (set.contains(nums[i])){
                return true;
            }

            set.add(nums[i]);

            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;

    }
}
