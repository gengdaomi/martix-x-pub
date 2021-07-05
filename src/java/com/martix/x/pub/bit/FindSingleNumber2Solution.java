package com.martix.x.pub.bit;

import java.util.*;

/**
 * Created by Andrew-Geng on 12:49 上午 2021/4/27
 * 数组中数字出现的次数
 * lc 260
 * 剑指 Offer 56 - I
 * <p>
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 */
public class FindSingleNumber2Solution {

    public static void main(String[] args) {
        Arrays.stream(new FindSingleNumber2Solution().singleNumbers_1(new int[]{4, 1, 4, 6}))
                .forEach(System.out::println);
    }

    /**
     * https://blog.csdn.net/morewindows/article/details/8214003
     * <p>
     * 设题目中这两个只出现1次的数字分别为A和B，如果能将A，B分开到二个数组中，那显然符合“异或”解法的关键点了。
     * 因此这个题目的关键点就是将A，B分开到二个数组中。由于A，B肯定是不相等的，因此在二进制上必定有一位是不同的。
     * 根据这一位是0还是1可以将A，B分开到A组和B组。
     * 而要判断A，B在哪一位上不相同，只要根据A异或B的结果就可以知道了，这个结果在二进制上为1的位都说明A，B在这一位上是不相同的。
     *
     * 此时我们要找到一个操作，让两个数字进行这个操作后，分为两组。
     * 我们最容易想到的就是 & 1 操作， 当我们对奇偶分组时，容易地想到 & 1，即用于判断最后一位二进制是否为 1。来辨别奇偶。
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * @param nums
     * @return
     */
    public int[] singleNumbers(int[] nums) {
        int k = 0;
        for (int num : nums) {
            k ^= num;
        }

        int maskCode = 1; //获得k中最低位为1的掩码
        while ((k & maskCode) == 0) {////mask = k & (-k) 这种方法也可以得到mask，
            maskCode <<= 1;
        }

        int a = 0, b = 0; //分为a b两个子串 各自去算异或
        for (int num : nums) {
            if ((num & maskCode) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }

        return new int[]{a, b};
    }

    /**
     * 不考虑空间复杂度 O(n)
     * @param nums
     * @return
     */
    public int[] singleNumbers_1(int[] nums){
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }

        int[] result = new int[set.size()];
        int i = 0;
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            result[i] = iterator.next();
            i++;
        }

        return result;
    }
}
