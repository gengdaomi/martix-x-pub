package com.martix.x.pub.code.number;

/**
 * Created by Andrew-Geng on 15:54 2023/1/5
 * 数字序列中某一位的数字 剑指 Offer 44.
 *
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 *
 * 请写一个函数，求任意第n位对应的数字。
 *
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：3
 * 示例 2：
 *
 * 输入：n = 11
 * 输出：0
 *
 *
 */
public class FindNthDigitsSolution {

    /**
     * 二分查找
     *
     * 为了得到无限整数序列中的第n 位数字，需要知道第n 位数字是哪一个整数的第几位。
     * 如果知道第n 位数字所在整数是几位数，就能计算得到第n 位数字是哪一个整数的第几位。
     *
     * 假设第n 位数字所在整数是d 位数，则所有位数不超过d−1 的整数的位数之和小于n，且所有位数不超过d 的整数的位数之和大于等于n。
     * 由于所有位数不超过x 的整数的位数之和关于x 单调递增，因此可以使用二分查找确定上述d 的值。
     * 对于某个x，如果所有位数不超过x 的整数的位数之和小于n，则d>x，否则d≤x，以此调整二分查找的上下界。
     *
     * 由于任何整数都至少是一位数，因此d 的最小值是1。对于d 的上界，可以通过找规律的方式确定。
     * 1 位数的范围是1 到 9，共有9 个数，所有1 位数的位数之和是1×9=9。
     * 2 位数的取值范围是 10 到 99，共有90 个数，所有2 位数的位数之和是2×90=180。
     * 3 位数的取值范围是100 到 999，共有900 个数，所有3 位数的位数之和是3×900=2700。
     * ……
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        int low = 1, high = 9;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (totalDigits(mid) < n) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        int d = low;
        int prevDigits = totalDigits(d - 1);
        int index = n - prevDigits - 1;
        int start = (int) Math.pow(10, d - 1);
        int num = start + index / d;
        int digitIndex = index % d;
        int digit = (num / (int) (Math.pow(10, d - digitIndex - 1))) % 10;
        return digit;
    }

    public int totalDigits(int length) {
        int digits = 0;
        int curLength = 1, curCount = 9;
        while (curLength <= length) {
            digits += curLength * curCount;
            curLength++;
            curCount *= 10;
        }
        return digits;
    }
}
