package com.martix.x.bit;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew-Geng on 10:38 下午 2021/4/23
 * 丢失的数字
 * <p>
 * lc 286
 * <p>
 * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 * <p>
 *  
 * <p>
 * 进阶：
 * <p>
 * 你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,0,1]
 * 输出：2
 * 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 2：
 * <p>
 * 输入：nums = [0,1]
 * 输出：2
 * 解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 3：
 * <p>
 * 输入：nums = [9,6,4,2,3,5,7,0,1]
 * 输出：8
 * 解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 4：
 * <p>
 * 输入：nums = [0]
 * 输出：1
 * 解释：n = 1，因为有 1 个数字，所以所有的数字都在范围 [0,1] 内。1 是丢失的数字，因为它没有出现在 nums 中。
 */
public class FindMissingNumberSolution {

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1};
        System.out.println(new FindMissingNumberSolution().missingNumber(nums));
    }

    /**
     * 借助异或思路，这里主要是借助 数组下标和数组的值 依次进行异或，
     * 我们可以理解缺失的数字，应该是下标值有，但是数组中没有；而正常的是数组和下标都出现，借助异或后就等于0
     * <p>
     * 异或运算满足交换律，a^b^a=a^a^b=b
     * <p>
     * 时间复杂度O(n) , 空间复杂度O(1)
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int missNum = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missNum ^= i ^ nums[i];
        }

        return missNum;
    }

    /**
     * 借助数学和的思路来找到 缺失的数字，
     * <p>
     * 先算出数组下标的[0..nums.length]的总和 (nums.length+1)*nums.length/2
     * <p>
     * 然后算出nums的值总和
     * <p>
     * 两者之差为缺失的数字
     * <p>
     * 时间复杂度 O(n)，空间复杂度O(1)
     *
     * @param nums
     * @return
     */
    public int missingNumber_1(int[] nums) {
        int sumIndex = nums.length * (nums.length + 1) / 2;

        int sumNum = 0;
        for (int num : nums) {
            sumNum += num;
        }

        return sumIndex - sumNum;
    }

    /**
     * 借助hash表的思路
     * <p>
     * 时间复杂度 O(n)，空间复杂度O(n)
     *
     * @param nums
     * @return
     */
    public int missingNumber_2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) { //先把真实数组的数字放进去
            map.put(num, 1);
        }

        for (int i = 0; i <= nums.length; i++) { //依次用数组下标去比较map,当不存在的就是缺失的数字
           if(!map.containsKey(i)){
               return i;
           }
        }

        return -1;
    }
}
