package com.martix.x.pub.code.number;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew-Geng on 10:21 2022/12/20
 * 多数元素 II lc229
 *
 * 给定一个大小为n的整数数组，找出其中所有出现超过⌊ n/3 ⌋次的元素。

 *
 * 示例1：
 *
 * 输入：nums = [3,2,3]
 * 输出：[3]
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：[1]
 * 示例 3：
 *
 * 输入：nums = [1,2]
 * 输出：[1,2]
 *
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。
 *
 */
public class MajorityElementModeArraySolution {

    /**
     * 摩尔投票法
     * 摩尔投票法：摩尔投票法的核心思想为对拼消耗。首先我们考虑最基本的摩尔投票问题，比如找出一组数字序列中出现次数大于总数1/2的数字(并且假设这个数字一定存在)
     * 每次从序列里选择两个不相同的数字删除掉（或称为「抵消」），最后剩下一个数字或几个相同的数字，就是出现次数大于总数一半的那个元素。
     *
     * 利用反证法推断出满足这样条件的元素最多只有两个，我们可以利用摩尔投票法的核心思想，每次选择三个互不相同的元素进行删除（或称为「抵消」）。
     * 我们可以假设数组中一定只存在一个次数大于n/3的元素x，其中n 为数组的长度，则此时我们可以把数组分成两部分：
     * 一部分相同的k 个元素x，另一部分为(n-k)/3组三个不同的元素，我们知道三个不同的元素会被抵消，因此最终只会剩下一个元素为x;
     *
     * 如果只存在2 个次数大于n/3的元素时，我们假设这两个不同的元素分别为x 和y，则此时我们一定可以把数组分成三部分：第一部分相同的m 个元素x，
     * 第二部分相同的k 个元素y，第三部分为(n-m-k)/3组三个互不同的元素，我们知道三个互不同的元素会被抵消，因此最终只会剩下两个元素为x和y。
     *
     * 核心算法：
     * 1.我们每次检测当前元素是否为第一个选中的元素或者第二个选中的元素。
     * 2.每次我们发现当前元素与已经选中的两个元素都不相同，则进行抵消一次。
     * 3.如果存在最终选票大于0 的元素，我们还需要再次统计已选中元素的次数,检查元素的次数是否大于n/3
     *
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        int element1 = 0;
        int element2 = 0;
        int vote1 = 0;
        int vote2 = 0;

        for (int num : nums) {
            if (vote1 > 0 && num == element1) { //如果该元素为第一个元素，则计数加1
                vote1++;
            } else if (vote2 > 0 && num == element2) { //如果该元素为第二个元素，则计数加1
                vote2++;
            } else if (vote1 == 0) { // 选择第一个元素
                element1 = num;
                vote1++;
            } else if (vote2 == 0) { // 选择第二个元素
                element2 = num;
                vote2++;
            } else { //如果三个元素均不相同，则相互抵消1次
                vote1--;
                vote2--;
            }
        }

        int count1 = 0;
        int count2 = 0;
        for (int num : nums) {
            if (vote1 > 0 && num == element1) {
                count1++;
            }
            if (vote2 > 0 && num == element2) {
                count2++;
            }
        }
        // 检测元素出现的次数是否满足要求
        List<Integer> result = new ArrayList<>();
        if (vote1 > 0 && count1 > nums.length / 3) {
            result.add(element1);
        }
        if (vote2 > 0 && count2 > nums.length / 3) {
            result.add(element2);
        }

        return result;
    }

    /**
     * 哈希表法
     *
     * 哈希统计数组中每个元素出现的次数，设数组的长度为n，返回所有统计次数超过n/3次的数字
     *
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     * @param nums
     * @return
     */
    public List<Integer> majorityElement_1(int[] nums) {
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            if (count.containsKey(nums[i])) {
                count.put(nums[i], count.get(nums[i]) + 1);
            } else {
                count.put(nums[i], 1);
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int x : count.keySet()) {
            if (count.get(x) > nums.length / 3) {
                result.add(x);
            }
        }

        return result;
    }
}
