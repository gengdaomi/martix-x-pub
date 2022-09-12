package com.martix.x.pub.code.bit;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew-Geng on 10:17 下午 2021/4/23
 * <p>
 * 只出现一次的数字
 * lc 136
 * <p>
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: [4,1,2,1,2]
 * 输出: 4
 */
public class FindSingleNumberSolution {

    public static void main(String[] args) {
        int[] nums = new int[]{4, 1, 2, 1, 2};
        System.out.println(new FindSingleNumberSolution().singleNumber_1(nums));
    }

    /**
     * 异或 位操作
     * 因为数组 除了1个是单数外 其余都是两个，
     * <p>
     * 最优解
     * <p>
     * 异或运算满足交换律，a^b^a=a^a^b=b
     * <p>
     * 时间复杂度O(n) , 空间复杂度O(1)
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int single = 0;

        for (int num : nums) {
            single ^= num;
        }

        return single;
    }

    /**
     * 其他解法  排序 O(nlogn)
     * <p>
     * Hash 表的方式 时间 空间 O(n)
     */
    public int singleNumber_1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            if (map.containsKey(num)) {
                map.remove(num);
            } else {
                map.put(num, 1);
            }
        }

        for (int key : map.keySet()) { //这个时候 map只有一个
            return key;
        }

        return -1;
    }
}
