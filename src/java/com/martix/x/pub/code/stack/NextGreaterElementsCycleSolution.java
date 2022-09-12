package com.martix.x.pub.code.stack;

import java.util.Stack;

/**
 * Created by Andrew-Geng on 2:11 下午 2021/5/16
 * 下一个更大元素 II
 * lc 503
 * <p>
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），
 * 输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，
 * 这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 */
public class NextGreaterElementsCycleSolution {

    public static void main(String[] args) {
        int[] num1 = new int[]{2,1, 2,4,3};

        int[] result = new NextGreaterElementsCycleSolution().nextGreaterElements(num1);
        System.out.println(result);

    }

    /**
     * 单调栈 + 循环数组
     * 单调栈中保存的是下标，从栈底到栈顶的下标在数组nums 中对应的值是单调不升的。
     *
     * 我们可以把这个循环数组「拉直」，即复制该序列的前n−1 个元素拼接在原序列的后面。
     * 这样我们就可以将这个新序列当作普通序列，用上文的方法来处理。
     * 在本题中，我们不需要显性地将该循环数组「拉直」，而只需要在处理时对下标取模即可;
     *
     * 时间空间复杂度O(n); n是序列的长度
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        int[] result = new int[nums.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 2 * nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i % nums.length]) {
                stack.pop();
            }

            result[i % nums.length] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i % nums.length]);
        }

        return result;
    }
}
