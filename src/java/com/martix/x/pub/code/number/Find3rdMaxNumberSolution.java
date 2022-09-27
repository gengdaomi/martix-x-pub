package com.martix.x.pub.code.number;

import java.util.TreeSet;

/**
 * Created by Andrew-Geng on 01:07 2022/9/25
 * 第三大的数 lc 414
 *
 * 给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。
 *
 * 输入：[3, 2, 1]
 * 输出：1
 * 解释：第三大的数是 1 。
 * 示例 2：
 *
 * 输入：[1, 2]
 * 输出：2
 * 解释：第三大的数不存在, 所以返回最大的数 2 。
 * 示例 3：
 *
 * 输入：[2, 2, 3, 1]
 * 输出：1
 * 解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
 * 此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。
 *
 */
public class Find3rdMaxNumberSolution {

    /**
     * 一次遍历
     *
     * 可以遍历数组，并用三个变量
     * a、b 和c 来维护数组中的最大值、次大值和第三大值；
     *
     * 遍历数组，对于数组中的元素num：
     *
     * 若 num>a，我们将c 替换为  b，b 替换为a， a 替换为num，这模拟了将 num 插入有序集合，并删除有序集合中的最小值的过程；
     * 若 a>num>b，类似地，我们将c 替换为b，b 替换为num，a 保持不变；
     * 若b>num>c，类似地，我们将c 替换为num，a 和b 保持不变；
     * 其余情况不做处理。
     * @param nums
     * @return
     */
    public int thirdMax(int[] nums) {
        long a = Long.MIN_VALUE, b = Long.MIN_VALUE, c = Long.MIN_VALUE;

        for (long num : nums) {
            if (num > a) {
                c = b;
                b = a;
                a = num;
            } else if (a > num && num > b) {
                c = b;
                b = num;
            } else if (b > num && num > c) {
                c = num;
            }
        }

        return c == Long.MIN_VALUE ? (int) a : (int) c;
    }

    /**
     * 一次遍历 优化版
     * 将a和b和c初始化为空指针或空对象，视作「无穷小」，并在比较大小前先判断是否为空指针或空对象。遍历结束后，
     * 若c 为空，则说明第三大的数不存在，返回a，否则返回c
     *
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     * @param nums
     * @return
     */
    public int thirdMax_1(int[] nums) {
        Integer a = null, b = null, c = null;

        for (int num : nums) {
            if (a == null || num > a) {
                c = b;
                b = a;
                a = num;
            } else if (a > num && (b == null || num > b)) {
                c = b;
                b = num;
            } else if (b != null && b > num && (c == null || num > c)) {
                c = num;
            }
        }

        return c == null ? a : c;
    }

    /**
     * 有序集合
     *
     * 遍历数组，同时用一个有序集合来维护数组中前三大的数。具体做法是每遍历一个数，就将其插入有序集合，若有序集合的大小超过3，
     * 就删除集合中的最小元素。这样可以保证有序集合的大小至多为3，且遍历结束后，若有序集合的大小为3，
     * 其最小值就是数组中第三大的数；若有序集合的大小不足3，那么就返回有序集合中的最大值。
     *
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     * @param nums
     * @return
     */
    public int thirdMax_2(int[] nums) {
        TreeSet<Integer> s = new TreeSet<Integer>();
        for (int num : nums) {
            s.add(num);
            if (s.size() > 3) {
                s.remove(s.first());
            }
        }

        return s.size() == 3 ? s.first() : s.last();
    }
}
