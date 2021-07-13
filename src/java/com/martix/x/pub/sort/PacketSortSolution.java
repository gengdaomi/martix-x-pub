package com.martix.x.pub.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew-Geng on 11:53 下午 2021/5/27
 * <p>
 * 煎饼排序 lc 969
 * <p>
 * 给你一个整数数组 arr ，请使用 煎饼翻转 完成对数组的排序。
 * <p>
 * 一次煎饼翻转的执行过程如下：
 * 选择一个整数 k ，1 <= k <= arr.length
 * 反转子数组 arr[0...k-1]（下标从 0 开始）
 * 例如，arr = [3,2,1,4] ，选择 k = 3 进行一次煎饼翻转，反转子数组 [3,2,1] ，得到 arr = [1,2,3,4] 。
 * <p>
 * 以数组形式返回能使 arr 有序的煎饼翻转操作所对应的 k 值序列。任何将数组排序且翻转次数在10 * arr.length 范围内的有效答案都将被判断为正确。
 *
 * <p>
 * 示例 1：
 * <p>
 * 输入：[3,2,4,1]
 * 输出：[4,2,4,3]
 * 解释：
 * 我们执行 4 次煎饼翻转，k 值分别为 4，2，4，和 3。
 * 初始状态 arr = [3, 2, 4, 1]
 * 第一次翻转后（k = 4）：arr = [1, 4, 2, 3]
 * 第二次翻转后（k = 2）：arr = [4, 1, 2, 3]
 * 第三次翻转后（k = 4）：arr = [3, 2, 1, 4]
 * 第四次翻转后（k = 3）：arr = [1, 2, 3, 4]，此时已完成排序。
 * 示例 2：
 * <p>
 * 输入：[1,2,3]
 * 输出：[]
 * 解释：
 * 输入已经排序，因此不需要翻转任何内容。
 * 请注意，其他可能的答案，如 [3，3] ，也将被判断为正确。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 100
 * 1 <= arr[i] <= arr.length
 * arr 中的所有整数互不相同（即，arr 是从 1 到 arr.length 整数的一个排列）
 */
public class PacketSortSolution {

    public static void main(String[] args) {
        int[] array = new int[]{3, 2, 4, 1};
        List<Integer> result = new PacketSortSolution().pancakeSort(array);
        System.out.println(result);
    }

    private List<Integer> result = new ArrayList<>();

    /**
     * 1.找到n个烧饼中最大的那个
     * 2.把这个最大的移到最底下
     * 3.递归调用
     * <p>
     * 如何将某块移到最后面呢
     * 比如第3块饼是最大的，我们想把它换到最后，也就是换到第n块
     * 1.用锅铲把前3块饼翻转一下，这样最大的就到最上面
     * 2.用锅铲将前n块饼全部翻转，这样最大的就翻到第n块，也就是最后一块
     *
     * @param arr
     * @return
     */
    public List<Integer> pancakeSort(int[] arr) {
        sort(arr, arr.length);
        return result;
    }

    /**
     * 将前n块烧饼反转
     *
     * @param cakes
     * @param n
     */
    public void sort(int[] cakes, int n) {
        if (n == 1) {
            return;
        }

        int maxCake = 0;
        int maxCakeIndex = 0;

        for (int i = 0; i < n; i++) {
            if (cakes[i] > maxCake) {
                maxCakeIndex = i;
                maxCake = cakes[i];
            }
        }

        this.reverse(cakes, 0, maxCakeIndex); //第一次翻转，将最大烧饼翻到最上面
        result.add(maxCakeIndex + 1);

        this.reverse(cakes, 0, n - 1); //第二次翻转，将最大的反转到最底下
        result.add(n);

        sort(cakes, n - 1);
    }

    /**
     * 反转 array[i..j]
     *
     * @param array
     * @param i
     * @param j
     */
    private void reverse(int[] array, int i, int j) {
        while (i < j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;

            i++;
            j--;
        }
    }
}
