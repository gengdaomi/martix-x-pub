package com.martix.x.pub.gather;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Andrew-Geng on 12:55 上午 2021/6/2
 * 两个数组的交集 lc 349
 * <p>
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 示例 2：
 * <p>
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 */
public class IntersectionSolution {

    /**
     * 通过 set集合的处理方式
     * <p>
     * 时间复杂度：
     * 空间复杂度 都是O(n+m),即两个数组的大小之和
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for (int num : nums1) {
            set1.add(num);
        }

        for (int num : nums2) {
            set2.add(num);
        }

        return set1.size() > set2.size() ? this.intersect(set2, set1)
                : this.intersect(set1, set2);
    }

    /**
     * 保障 遍历长度小的
     *
     * @param nums1 这个是长度小的
     * @param nums2
     * @return
     */
    private int[] intersect(Set<Integer> nums1, Set<Integer> nums2) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums1) {
            if (nums2.contains(num)) {
                set.add(num);
            }
        }

        int[] result = new int[set.size()];
        int index = 0;
        for (int num : set) {
            result[index++] = num;
        }

        return result;
    }

    /**
     * 排序+双指针
     * <p>
     * 时间复杂度：O(mlogm+nlogn)
     * 空间复杂度：O(logm+logn)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection_1(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int[] intersection = new int[nums1.length + nums2.length];
        int index = 0, index1 = 0, index2 = 0;

        while (index1 < nums1.length && index2 < nums2.length) {
            int num1 = nums1[index1];
            int num2 = nums2[index2];

            if (num1 == num2) {
                // 保证加入元素的唯一性
                if (index == 0 || num1 != intersection[index - 1]) {
                    intersection[index++] = num1;
                }

                index1++;
                index2++;
            } else if (num1 > num2) {
                index2++;
            } else {
                index1++;
            }
        }

        return Arrays.copyOfRange(intersection, 0, index);
    }
}
