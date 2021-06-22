package com.martix.x.pub.heap;

import java.util.Arrays;

/**
 * Created By Andrew-Geng on 2020/11/10 4:10 下午
 * 优先级对列  借助堆
 */
public class PriorityQueueSolution {
    int[] array = new int[10];
    int size;

    //交换
    public void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    //向上调整
    public void shiftUp(int[] array, int childIndex) {
        int parentIndex = (childIndex - 1) / 2;

        while (childIndex > 0) {
            if (array[childIndex] < array[parentIndex]) {
                swap(array, childIndex, parentIndex);
                childIndex = parentIndex;
                parentIndex = (childIndex - 1) / 2;
            } else {
                break;
            }
        }
    }

    //向下调整
    public void shiftDown(int[] array, int size, int parentIndex) {
        int childIndex = 2 * parentIndex + 1;

        while (childIndex < size) {
            if (childIndex + 1 < size && array[childIndex + 1] < array[childIndex]) {
                childIndex++;
            }

            if (array[childIndex] < array[parentIndex]) {
                swap(array, childIndex, parentIndex);
                parentIndex = childIndex;
                childIndex = 2 * parentIndex + 1;
            } else {
                break;
            }
        }
    }

    //入队
    public void offer(int value) {
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }

        array[size++] = value;    //尾插
        shiftUp(array, size - 1);   //向上调整
    }

    //出队
    public int poll() {
        if (size > 0) {
            int peek = array[0];
            swap(array, 0, size - 1);
            size--;

            shiftDown(array, size, 0);
            return peek;
        }

        return -1;
    }

    //取队顶元素
    public int peek() {
        return array[0];
    }

    //判空
    public boolean isEmpty() {
        return size == 0;
    }
}
