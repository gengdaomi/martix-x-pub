package com.martix.x.pub.code.gather;

import java.util.*;

/**
 * Created by Andrew-Geng on 12:15 上午 2021/6/3
 * 两个数组的交集 II lc 350
 * <p>
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * 示例 2:
 * <p>
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 *  
 * <p>
 * 说明：
 * <p>
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶：
 * <p>
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果nums1的大小比nums2小很多，哪种方法更优？
 * 如果nums2的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 */
public class Intersection2Solution {

    public static void main(String[] args) {
        int[] num1 = new int[]{1, 2, 2, 1};
        int[] num2 = new int[]{2, 2};

        int[] result = new Intersection2Solution().intersect_1(num1, num2);
        System.out.println(result);

    }

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
    public int[] intersect_1(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums1) {
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
        }

        int[] intersection = new int[nums1.length];
        int index = 0;
        for (int num : nums2) {
            int count = map.getOrDefault(num, 0);
            if (count > 0) {
                intersection[index++] = num;
                count--;
                if (count > 0) {
                    map.put(num, count);
                } else {
                    map.remove(num);
                }
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }

    /**
     * @param map1
     * @param map2
     * @return
     */
    private int[] intersect(Map<Integer, Integer> map1, Map<Integer, Integer> map2) {
        List<Integer> result = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : map2.entrySet()) {

            while (map1.get(entry.getKey())!=null && map1.get(entry.getKey())>0) {
                result.add(entry.getKey());

                if (map1.get(entry.getKey()) > 0) {
                    map1.put(entry.getKey(), map1.getOrDefault(entry.getKey(), 0) - 1);
                } else {
                    map1.remove(entry.getKey());
                }
            }
        }

        int[] arr = new int[result.size()];
        int index = 0;
        for (int num : result) {
            arr[index++] = num;
        }
        return arr;
    }

    /**
     * 排序+双指针
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int length1 = nums1.length, length2 = nums2.length;

        int[] intersection = new int[Math.min(length1, length2)];

        int index1 = 0, index2 = 0, index = 0;
        while (index1 < length1 && index2 < length2) {
            if (nums1[index1] < nums2[index2]) {
                index1++;
            } else if (nums1[index1] > nums2[index2]) {
                index2++;
            } else {
                intersection[index] = nums1[index1];
                index1++;
                index2++;
                index++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }
}
