package com.martix.x.pub.code.arrays;

import java.util.PriorityQueue;

/**
 * Created by Andrew-Geng on 00:11 2022/9/24
 * 最大数 lc 179
 * <p>
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * <p>
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * <p>
 * 输入：nums = [10,2]
 * 输出："210"
 * 示例 2：
 * <p>
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 */
public class MaxNumberSolution {

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
     * <p>
     * x “大于”y 代表：排序完成后，数组中x 应在y 左边；“小于” 则反之。
     * <p>
     * 时间复杂度O(nlogn)
     *
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        String[] strArr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strArr[i] = String.valueOf(nums[i]);
        }

        quickSort(strArr, 0, strArr.length - 1); //通过快速排序 按照字符串正序排序

        if (strArr[strArr.length - 1].equals("0")) { //当最大的一个值是0 则整个值就是0
            return "0";
        }

        StringBuffer sb = new StringBuffer();
        for (int i = strArr.length - 1; i >= 0; i--) { //因为是数组是正序排序，所以倒序遍历则是最大值
            sb.append(strArr[i]);
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
     * <p>
     * 设数组nums 中任意两数字的字符串为
     * x 和y ，则规定 排序判断规则 为：
     * 若拼接字符串
     * x+y>y+x ，则
     * x “大于”y ；
     * 反之，若x+y<y+x ，则
     * x “小于”y
     * <p>
     * x “大于”y 代表：排序完成后，数组中x 应在y 左边；“小于” 则反之。
     *
     * @param nums
     * @return
     */
    public String largestNumber_1(int[] nums) {
        PriorityQueue<String> heap = new PriorityQueue<>((x, y) -> (y + x).compareTo(x + y));
        for (int x : nums) {
            heap.offer(String.valueOf(x));
        }

        String result = "";
        while (heap.size() > 0) {
            result += heap.poll();
        }

        if (result.charAt(0) == '0') {
            return "0";
        }

        return result;
    }
}
