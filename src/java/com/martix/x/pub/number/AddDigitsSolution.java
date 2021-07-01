package com.martix.x.pub.number;

/**
 * Created by Andrew-Geng on 12:03 上午 2021/4/27
 * 各位相加 lc 258
 * <p>
 * 示例:
 * <p>
 * 输入: 38
 * 输出: 2
 * 解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于2 是一位数，所以返回 2。
 * 进阶:
 * 你可以不使用循环或者递归，且在 O(1) 时间复杂度内解决这个问题吗？
 */
public class AddDigitsSolution {

    public static void main(String[] args) {
        System.out.println(new AddDigitsSolution().addDigits(38));
    }

    public int addDigits(int nums) {
        if (nums < 10) {
            return nums;
        }

        while (nums >= 10) {
            nums = this.getNextSum(nums);
        }
        return nums;
    }

    /**
     * 除个位外，每一位上的值都是通过 (9+1) 进位的过程得到的
     *
     * @param nums
     * @return
     */
    public int addDigits_1(int nums) {
        return (nums - 1) % 9 + 1;
    }

    /**
     * 能够被9整除的整数，各位上的数字加起来也必然能被9整除，所以，连续累加起来，最终必然就是9。
     * 不能被9整除的整数，各位上的数字加起来，结果对9取模，和初始数对9取摸，是一样的，所以，连续累加起来，最终必然就是初始数对9取摸。
     *
     * @param nums
     * @return
     */
    public int addDigits_2(int nums) {
        if (0 == nums % 9) {
            return 9;
        }

        return nums % 9;
    }

    /**
     * 获得下一次相加值
     *
     * @param nums
     * @return
     */
    private int getNextSum(int nums) {
        int result = 0;
        while (nums != 0) {
            int temp = nums % 10;

            result += temp;
            nums /= 10;
        }

        return result;
    }
}
