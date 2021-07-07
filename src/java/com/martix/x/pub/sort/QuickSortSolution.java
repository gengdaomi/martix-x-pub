package com.martix.x.pub.sort;

import java.util.Arrays;

/**
 * Created by ayue on 下午3:07 2018/6/27
 * 快速排序  时间复杂度为O(nlogn)
 */
public class QuickSortSolution {

    public static void main(String[] args) {
        int[] integers = new int[]{33, 1, 3, 234, 5, 3435, 32, 34};

        sort(integers, 0, integers.length - 1);

        System.out.println(Arrays.toString(integers));
    }

    /**
     * 1.首先设定一个分界值，通过该分界值将数组分成左右两部分;
     * <p>
     * 2.将大于或等于分界值的数据集中到数组右边，小于分界值的数据集中到数组的左边。
     * 此时，左边部分中各元素都小于或等于分界值，而右边部分中各元素都大于或等于分界值;
     * <p>
     * 3.然后，左边和右边的数据可以独立排序。对于左侧的数组数据，又可以取一个分界值，将该部分数据分成左右两部分，同样在左边放置较小值，右边放置较大值。
     * 右侧的数组数据也可以做类似处理;
     * <p>
     * 4.重复上述过程，可以看出，这是一个递归定义
     * <p>
     * <p>
     * 整个快速排序算法的时间复杂度与划分的趟数有关;
     * 理想的情况是，每次划分所选择的中间数恰好将当前序列几乎等分，经过log2n趟划分，便可得到长度为1的子表。这样，整个算法的时间复杂度为O(nlog2n)
     * 最坏的情况是，每次所选的中间数是当前序列中的最大或最小元素，这使得每次划分所得的子表中一个为空表，另一子表的长度为原表的长度-1;
     * 这样，长度为n的数据表的快速排序需要经过n趟划分，使得整个排序算法的时间复杂度为O(n^2)
     * <p>
     * 快速排序的平均时间复杂度也是O(nlog2n);
     * <p>
     * 尽管快速排序只需要一个元素的辅助空间，但快速排序需要一个栈空间来实现递归;
     * 最好的情况下，即快速排序的每一趟排序都将元素序列均匀地分割成长度相近的两个子表，所需栈的最大深度为log2(n+1)；
     * 但最坏的情况下，栈的最大深度为n。这样，快速排序的空间复杂度为O(log2n)
     *
     * @param params
     * @param low
     * @param high
     */
    private static void sort(int[] params, int low, int high) {
        if (params == null || params.length == 0) {
            return;
        }
        if (low > high) {
            return;
        }

        int i = low;
        int j = high;

        Integer partition = params[i]; // 用子表的第一个记录做基准
        while (i < j) {// 从表的两端交替向中间扫描

            while (i < j && params[j] >= partition) {
                j--;
            }

            if (i < j) {
                params[i++] = params[j];// 用比基准小的记录替换低位记录
            }

            while (i < j && params[i] < partition) {
                i++;
            }

            if (i < j) {// 用比基准大的记录替换高位记录
                params[j--] = params[i];
            }
        }

        params[i] = partition; // 将基准数值替换回 a[i]

        sort(params, low, i - 1); // 对低子表进行递归排序
        sort(params, i + 1, high); // 对高子表进行递归排序
    }
}
