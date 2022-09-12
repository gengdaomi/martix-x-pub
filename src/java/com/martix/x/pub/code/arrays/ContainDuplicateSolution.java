package com.martix.x.pub.code.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Andrew-Geng on 10:22 下午 2021/6/26
 * 存在重复元素 lc 217
 * <p>
 * 给定一个整数数组，判断是否存在重复元素。
 * <p>
 * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,1]
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: false
 * 示例3:
 * <p>
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: tru
 */
public class ContainDuplicateSolution {

    /**
     * 时空复杂度O(N)
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }

            set.add(num);
        }

        return false;
    }

    /**
     * 排序 时间复杂度O(NlogN) 空间复杂度O(logN)
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate_1(int[] nums) {
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

}
