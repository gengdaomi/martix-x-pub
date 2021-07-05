package com.martix.x.pub.bit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew-Geng on 1:13 上午 2021/4/24
 * <p>
 * 只出现一次的数字 II
 * lc 137
 * <p>
 * 给你一个整数数组nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,2,3,2]
 * 输出：3
 * 示例 2：
 * <p>
 * 输入：nums = [0,1,0,1,0,1,99]
 * 输出：99
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
 *  
 * <p>
 * 进阶：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 */
public class FindSingleNumber3Solution {

    public static void main(String[] args) {
        int[] nums = new int[]{0, 5, 0, 5, 0, 5, 6};
        System.out.println(new FindSingleNumber3Solution().singleNumber_1(nums));
    }

    /**
     * 由于数组中的元素都在int（即32 位整数）范围内，因此我们可以依次计算答案的每一个二进制位是
     * 0 还是1。
     *
     * 具体地，考虑答案的第i 个二进制位（i 从0 开始编号），
     * 它可能为0 或1。对于数组中非答案的元素，每一个元素都出现了3 次，对应着第
     * i 个二进制位的3 个0 或3 个1，无论是哪一种情况，它们的和都是3 的倍数（即和为0 或3）。因此：
     *
     * 答案的第i个二进制位就是数组中所有元素的第i 个二进制位之和除以3 的余数。
     *
     * 这样一来，对于数组中的每一个元素
     * x，我们使用位运算(x >> i) & 1得到x的第i 个二进制位，并将它们相加再对3 取余，
     * 得到的结果一定为0 或1即为答案的第i 个二进制位。
     *
     * 细节
     *
     * 需要注意的是，如果使用的语言对「有符号整数类型」和「无符号整数类型」没有区分，那么可能会得到错误的答案。这是因为「有符号整数类型」（即
     * int 类型）的第31 个二进制位（即最高位）是补码意义下的符号位，对应着
     * −2
     * 31
     *  ，而「无符号整数类型」由于没有符号，第31 个二进制位对应着
     * 2
     * 31
     *  。因此在某些语言（例如Python）中需要对最高位进行特殊判断。
     *
     * 时间复杂度：O(nlogC)  其中n 是数组的长度，C 是元素的数据范围,这里logC=32，因为int整型
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    public int singleNumber_1(int[] nums) {
        int result = 0;

        for (int i = 0; i < 32; i++) {  //整数32位，把数组nums中各个num的二进制，依次计算各个位置的1的个数
            int total = 0;
            for (int num : nums) {
                total += ((num >> i) & 1);  //num的第i个二进制位
            }

            if (total % 3 != 0) {
                result += (1 << i);
            }
        }

        return result;
    }

    /**
     * 数组中的元素都在
     * int（即
     * 32 位整数）范围内
     * 换一个角度来看，如果数组中没有x，那么数组中所有的数字都出现了3次，在二进制上，每位上1的个数肯定也能被3整除。如{1, 5, 1, 5, 1, 5}从二进制上看有：
     * <p>
     * 1：0001
     * <p>
     * 5：0101
     * <p>
     * 1：0001
     * <p>
     * 5：0101
     * <p>
     * 1：0001
     * <p>
     * 5：0101
     * <p>
     * 二进制第0位上有6个1，第2位上有3个1.第1位和第3位上都是0个1，每一位上的统计结果都可以被3整除。而再对该数组添加任何一个数，
     * 如果这个数在二进制的某位上为1都将导致该位上1的个数不能被3整除。因此通过统计二进制上每位1的个数就可以推断出x在该位置上是0还是1了，这样就能计算出x了。
     * <p>
     * 推广一下，所有其他数字出现N（N>=2）次，而一个数字出现1次都可以用这种解法来推导出这个出现1次的数字
     *
     * @param nums
     * @return
     */
    public int singleNumber_2(int[] nums) {
        int result = 0;

        int[] bitArr = new int[32];
        Arrays.fill(bitArr, 0);

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < 32; j++) {
                bitArr[j] += ((nums[i] >> j) & 1);
            }
        }

        for (int i = 0; i < 32; i++) {
            if (bitArr[i] % 3 != 0) {
                result += (1 << i);
            }
        }

        return result;
    }

    /**
     * 通过hash 解决
     * <p>
     * 时间 空间复杂度O(N)
     *
     * @param nums
     * @return
     */
    public int singleNumber_3(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int key : map.keySet()) {
            if (map.get(key) == 1) {
                return key;
            }
        }

        return -1;
    }
}
