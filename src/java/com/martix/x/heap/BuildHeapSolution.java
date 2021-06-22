package com.martix.x.heap;

/**
 * Created by Andrew-Geng on 10:16 下午 2021/5/26
 * 构建堆
 */
public class BuildHeapSolution {
    public static void main(String[] args) {

    }

    /**
     * 向上调整
     * <p>
     * 假设父节点的下标是parent，那么它的左孩子下标就是 2*parent+1；
     *
     * @param array
     */
    public void upAdjust(int[] array) {
        int childIndex = array.length - 1;
        int parentIndex = (childIndex - 1) / 2;

        int temp = array[childIndex]; // 用于交换
        while (childIndex > 0 && temp < array[parentIndex]) {
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (childIndex - 1) / 2;
        }

        array[parentIndex] = temp;
    }

    /**
     * 向下调整
     *
     * @param array
     * @param parentIndex
     * @param length
     */
    public void downAdjust(int[] array, int parentIndex, int length) {
        int temp = array[parentIndex]; //用于交换

        int childIndex = 2 * parentIndex + 1;

        while (childIndex < length) {
            if (childIndex + 1 < length && array[childIndex + 1] < array[childIndex]) { //如果有右孩子，且右孩子小于左孩子的值，则定位到右孩子
                childIndex++;
            }

            if (temp < array[childIndex]) { //如果父节点小于任何一个孩子的值，直接跳出
                break;
            }

            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * parentIndex + 1;
        }

        array[parentIndex] = temp;
    }

    /**
     * 构建堆  这个是构建最小堆
     * <p>
     * 构建二叉堆，也就是把一个无序的完全二叉树调整为二叉堆，本质上就是让所有非叶子节点依次下沉
     * 首先我们从最后一个非叶子节点(array.length - 2) / 2开始
     *
     * @param array
     */
    public void buildHeap(int[] array) {
        for (int i = (array.length - 2) / 2; i >= 0; i--) { //从最后一个非叶子节点开始，依次下沉调整
            this.downAdjust(array, i, array.length);
        }
    }
}
