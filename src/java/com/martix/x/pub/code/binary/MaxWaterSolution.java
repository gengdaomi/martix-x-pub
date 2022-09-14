package com.martix.x.pub.code.binary;

/**
 * Created by Andrew-Geng on 1:40 上午 2021/7/4
 * <p>
 * 容器盛水问题
 * <p>
 * 给定一个整形数组arr，已知其中所有的值都是非负的，将这个数组看作一个容器，请返回容器能装多少水;
 * <p>
 * 输入：
 * [3,1,2,5,2,4]
 * 复制
 * 返回值：5
 */
public class MaxWaterSolution {

    /**
     * 找出左右边界的最小值作为水位高度
     * 如果左边较低，则左边界向右遍历， 否则右边界向左移动
     * 如果当前标尺小于水位，则水量累加
     * <p>
     * 否则，将此标尺和右边边界高度进行比较，找出剩下数组中的新水位
     */
    public long maxWater(int[] arr) {
        if (arr == null || arr.length <= 2) {
            return 0;
        }

        int left = 0, right = arr.length - 1;
        long sum = 0;

        // 找出左右边界的最小值作为水位高度
        int mark = Math.min(arr[left], arr[right]);
        while (left < right) {
            // 如果左边较低，则左边界向右遍历， 否则右边界向左移动
            if (arr[left] < arr[right]) {
                left++;
                // 如果当前标尺小于水位，则水量累加
                if (arr[left] < mark) {
                    sum += mark - arr[left];
                } else { // 否则，将此标尺和右边边界高度进行比较，找出剩下数组中的新水位
                    mark = Math.min(arr[left], arr[right]);
                }
            } else {
                right--;
                // 同理，如果当前标尺小于水位，则水量累加
                if (arr[right] < mark) {
                    sum += mark - arr[right];
                } else { // 否则，将此标尺和左边界的高度进行比较，找出剩余数组中的新水位
                    mark = Math.min(arr[right], arr[left]);
                }
            }
        }

        return sum;
    }
}
