package com.martix.x.pub.code.arrays;

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

    public static void main(String[] args){
        int[] nums = new int[]{1,0,1,1};
        boolean t = new ContainsNearbyDuplicateSolution().containsNearbyDuplicate(nums,1);
        System.out.println(t);
    }

    /**
     * hash 表的方式
     *
     * 用散列表来维护这个k大小的滑动窗口
     *
     * 遍历数组，对于每个元素做以下操作：
     * 1.在散列表中搜索当前元素，如果找到了就返回 true
     * 2.在散列表中插入当前元素
     * 3.如果当前散列表的大小超过了k， 删除散列表中最旧的元素
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
