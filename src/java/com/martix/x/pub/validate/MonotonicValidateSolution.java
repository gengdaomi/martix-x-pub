package com.martix.x.pub.validate;

/**
 * Created by Andrew-Geng on 1:21 上午 2021/4/10
 * <p>
 * 如果数组是单调递增或单调递减的，那么它是单调的。
 * <p>
 * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
 * <p>
 * 当给定的数组 A 是单调数组时返回 true，否则返回 false。
 * <p>
 * lc 896
 * <p>
 * 输入：[1,2,2,3]
 * 输出：true
 * <p>
 * 输入：[6,5,4,4]
 * 输出：true
 * <p>
 * 输入：[1,3,2]
 * 输出：false
 */
public class MonotonicValidateSolution {

    public boolean isMonotonic(int[] A) {
        if (A.length == 1) {
            return true;
        }

        Boolean flag = sort(A, true) || sort(A, false); //两次遍历的产物

        return flag;
    }

    /**
     * 通过比较数组 是否按照isIncreasing单调的
     * <p>
     * <p>
     * 时间复杂度：
     * O(n)，其中
     * n 是数组
     * A 的长度。
     * 空间复杂度：
     * O(1)
     *
     * @param arr
     * @param isIncreasing
     * @return
     */
    private boolean sort(int[] arr, boolean isIncreasing) {
        boolean flag = true;
        if (isIncreasing) {
            for (int i = 1; i < arr.length; i++) {
                if (arr[i - 1] > arr[i]) {
                    flag = false;
                    break;
                }
            }
        }

        if (!isIncreasing) {
            for (int i = 1; i < arr.length; i++) {
                if (arr[i - 1] < arr[i]) {
                    flag = false;
                    break;
                }
            }
        }

        return flag;
    }

    /**
     * 一次遍历
     *
     * @param A
     * @return
     */
    public boolean isMonotonic_1(int[] A) {
        //初始化是否单调递增 是否单调递减
        boolean isIncrease = true, isDecrease = true;

        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] < A[i]) { //表示当前出现的情况 存在单调递增的，所以判断单调递减为false
                isDecrease = false;
            }

            if (A[i - 1] > A[i]) { //表示当前出现的情况 存在单调递减的，所以判断单调递增为false
                isIncrease = false;
            }
        }

        return isIncrease || isDecrease;
    }

    public static void main(String[] args) {
        int[] a = new int[]{6, 5, 4, 4};

        System.out.println(new MonotonicValidateSolution().isMonotonic_1(a));
    }
}
