package com.martix.x.pub.code.number;

import java.util.Arrays;

/**
 * Created by Andrew-Geng on 10:40 下午 2021/5/13
 * 把数组排成最小的数 offer 45
 * <p>
 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [10,2]
 * 输出: "102"
 * 示例2:
 * <p>
 * 输入: [3,30,34,5,9]
 * 输出: "3033459"
 * <p>
 * 提示:
 * <p>
 * 0 < nums.length <= 100
 * 说明:
 * <p>
 * 输出结果可能非常大，所以你需要返回一个字符串而不是整数
 * 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
 */
public class MinNumberSolution {

    public static void main(String[] args) {
        int[] num = new int[]{3, 30, 34, 5, 9};
        System.out.println(new MinNumberSolution().minNumber(num));
    }

    /**
     * 归根结底 就是对数组的中以字符串类型进行排序
     * <p>
     * 设数组nums 中任意两数字的字符串为
     * x 和y ，则规定 排序判断规则 为：
     * 若拼接字符串
     * x+y>y+x ，则
     * x “大于”y ；
     * 反之，若x+y<y+x ，则
     * x “小于”y
     * x “小于”y 代表：排序完成后，数组中x 应在y 左边；“大于” 则反之。
     * <p>
     * 时间复杂度O(nlogn)
     *
     * @param nums
     * @return
     */
    public String minNumber(int[] nums) {
        String[] strArr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strArr[i] = String.valueOf(nums[i]);
        }

        quickSort(strArr, 0, strArr.length - 1);

        StringBuffer sb = new StringBuffer();
        for (String str : strArr) {
            sb.append(str);
        }

        return sb.toString();
    }

    public void quickSort(String[] arr, int low, int high) {
        if (low >= high) {
            return;
        }

        int left = low, right = high;
        String partition = arr[left];
        while (left < right) {
            while ((arr[right] + arr[low]).compareTo(arr[low] + arr[right]) >= 0 && left < right) {
                right--;
            }

            while ((arr[left] + arr[low]).compareTo(arr[low] + arr[left]) <= 0 && left < right) {
                left++;
            }

            partition = arr[left];
            arr[left] = arr[right];
            arr[right] = partition;
        }

        arr[left] = arr[low];
        arr[low] = partition;

        quickSort(arr, low, left - 1);
        quickSort(arr, left + 1, high);
    }

    /**
     * 这个用语言自带的排序函数
     *
     * @param nums
     * @return
     */
    public String minNumber_1(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(strs, (x, y) -> (x + y).compareTo(y + x));

        StringBuilder res = new StringBuilder();
        for (String s : strs) {
            res.append(s);
        }

        return res.toString();
    }
}
