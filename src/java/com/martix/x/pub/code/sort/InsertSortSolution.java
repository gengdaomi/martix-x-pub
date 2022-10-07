package com.martix.x.pub.code.sort;

/**
 * Created by Ayue 2022/10/7 13:07
 * 插入排序
 */
public class InsertSortSolution {

    public static void main(String[] args) {
        int[] array={10,6,9,3,5};
        InsertSortSolution insertSortSolution  = new InsertSortSolution();
        insertSortSolution.sortBS(array);

        System.out.println(array);
    }

    /**
     * 核心思路：
     * 整个区间被分为
     * 1. 有序区间
     * 2. 无序区间
     * 每次选择无序区间的第一个元素，在有序区间内选择合适的位置插入；其中第一部分的排序也是通过再次拆分为两部分来进行的.
     *
     * 算法细节：
     * ①. 从第一个元素开始，该元素可以认为已经被排序
     * ②. 取出下一个元素，在已经排序的元素序列中从后向前扫描
     * ③. 如果该元素（已排序）大于新元素，将该元素移到下一位置
     * ④. 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
     * ⑤. 将新元素插入到该位置后
     * ⑥. 重复步骤②~⑤
     *
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * 稳定排序
     * @param array
     */
    public void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int tmp = array[i];
            int j = i - 1;

            for (; j >= 0; j--) {
                if (array[j] > tmp) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }

            array[j + 1] = tmp;
        }
    }

    /**
     * 插入算法 改进优化版（因为传统的插入排序效率较低）
     *
     * 改进算法思路：
     *
     * 1.首先仍然是要找到当前无序区间中的第一个数。
     * 2.通过二分查找，找到其应该移动到的位置。
     * 3.将有序数组从其应该移动到的位置开始，到数组末尾的数，全部向后搬移一个位置。
     * 4.将数字直接插入到相应位置。
     *
     * @param array
     */
    public void sortBS(int[] array){
        for(int i = 1; i < array.length; i++){

            int temp = array[i];
            int low = 0;
            int high = i - 1;

            while(low <= high){
                int mid = (low + high) / 2;
                if(temp < array[mid]){
                    high = mid - 1;
                }else{
                    low = mid + 1;
                }
            }

            for(int j = i; j >= low + 1; j--){
                array[j] = array[j - 1];
            }

            array[low] = temp;
        }
    }

}
