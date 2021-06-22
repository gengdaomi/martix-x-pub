package com.martix.x.shuffle;

import java.util.Arrays;
import java.util.Random;

/**
 * 题目：
 *
 * 打乱一个没有重复元素的数组。
 *
 * 示例:
 *
 * // 以数字集合 1, 2 和 3 初始化数组。
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 *
 * // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
 * solution.shuffle();
 *
 * // 重设数组到它的初始状态[1,2,3]。
 * solution.reset();
 *
 * // 随机返回数组[1,2,3]打乱后的结果。
 * solution.shuffle();
 *
 *
 *
 * 解决方案
 * 遍历数组，每次随机生成一个下标位置，使用 Random.nextInt()，然后交换当前遍历位置和随机生成的下标位置的数字，这样如果数组有 n 个数字，那么我们也随机交换了 n 组位置，从而达到了打乱数组的目的。
 *
 * 注意：Random.nextInt() 方法的作用是生成一个随机的 int 值，该值介于 [0,n) 的区间，也就是 0 到 n 之间的随机 int 值，包含 0 而不包含 n。
 *
 */
public class ShuffleArraySolution {

    private final int[] originalArray;

    private final int n;

    private final Random mRandom = new Random();

    public ShuffleArraySolution(int[] nums) {

        n = nums.length;

        originalArray = nums;
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {

        return originalArray;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        int[] randomArray = new int[n];

        for (int i = 0; i < n; i++) {
            randomArray[i] = originalArray[i];
        }

        for (int i = 0; i < n; i++) {

            int r = i + mRandom.nextInt(n-i);

            int temp = randomArray[r];

            randomArray[r] = randomArray[i];

            randomArray[i] = temp;
        }

        return randomArray;
    }

    public static void main(String[] args){
        int[] i = new int[]{1,2,3,4,5,6};
        ShuffleArraySolution shuffleArraySolution = new ShuffleArraySolution(i);

        int[] result = shuffleArraySolution.shuffle();
        Arrays.stream(result)
                .mapToObj(index->Integer.toString(index))
                .forEach(System.out::print);
    }
}
