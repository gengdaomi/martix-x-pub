package com.martix.x.pub.permutation;

/**
 * Created by Andrew-Geng on 10:41 下午 2021/5/17
 * <p>
 * 下一个更大元素 III  lc 556
 *
 * <p>
 * 给你一个正整数n ，请你找出符合条件的最小整数，其由重新排列 n中存在的每位数字组成，并且其值大于 n 。如果不存在这样的正整数，则返回 -1 。
 * <p>
 * 注意 ，返回的整数应当是一个 32 位整数 ，如果存在满足题意的答案，但不是 32 位整数 ，同样返回 -1 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 12
 * 输出：21
 * 示例 2：
 * <p>
 * 输入：n = 21
 * 输出：-1
 */
public class NextGreaterElement2Solution {

    public static void main(String[] args) {
        int n = 2147483468;

        int result = new NextGreaterElement2Solution().nextGreaterElement(n);
        System.out.println(result);
    }

    /**
     * 与lc 31 处理方式一样
     *
     * 思路：
     * * 从后往前查看逆序区域，找到逆序前一位，即数字交换的边界
     * * 让逆序区域的前一位和逆序区域中大于ta的最小数字交换位置
     * * 把原来逆序区域转为顺序的
     *
     * @param n
     * @return
     */
    public int nextGreaterElement(int n) {
        char[] charArr = Integer.toString(n).toCharArray(); //将整数转变为char数组

        int index = getReverseIndex(charArr);
        if (index == 0) {  //由于此数已经最大了，所以返回-1
            return -1;
        }

        this.exchange(charArr, index);
        this.reverse(charArr, index);

        long result = Long.parseLong(new String(charArr)); //防止整数溢出，比如 当n=2147483468，他的下一个大数是2147483648 已经超过Integer.Max_VALUE

        return result > Integer.MAX_VALUE ? -1 : (int) result;
    }

    /**
     * 从后往前 获取到数组中第一个逆序的位置
     *
     * @param nums
     * @return
     */
    private int getReverseIndex(char[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 表示从index位置开始进行数组倒序
     * @param nums
     * @param index
     * @return
     */
    private char[] reverse(char[] nums, int index) {
        int left = index, right = nums.length - 1;

        while (left < right) {
            nums[left] ^= nums[right];
            nums[right] ^= nums[left];
            nums[left] ^= nums[right];

            left++;
            right--;
        }

        return nums;
    }

    /**
     * 将从尾部到index子数组中仅仅大于index-1位置的值，与其交换
     *
     * @param nums
     * @param index
     */
    private void exchange(char[] nums, int index) {

        char head = nums[index - 1];

        for (int i = nums.length - 1; i >= index; i--) {
            if (head < nums[i]) {
                nums[index - 1] = nums[i];
                nums[i] = head;

                break;
            }
        }
    }

}
