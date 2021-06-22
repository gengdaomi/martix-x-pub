package com.martix.x.pub.queue;

import java.util.Arrays;

/**
 * Created By Andrew-Geng on 2020/11/10 4:10 下午
 * 优先级对列  借助堆
 */
public class PriorityQueueSolution {
    int[] arr = new int[10];
    int size;

    //交换
    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //向上调整
    public void shiftUp(int[] arr, int sz, int child) {
        int parent = (child - 1) / 2;
        while (child > 0) {
            if (arr[child] < arr[parent]) {
                swap(arr, child, parent);
                child = parent;
                parent = (child - 1) / 2;
            } else {
                break;
            }
        }
    }

    //向下调整
    public void shiftDown(int[] arr, int sz, int parent) {
        int child = 2 * parent + 1;
        while (child < sz) {
            if (child + 1 < sz && arr[child + 1] < arr[child]) {
                child++;
            }
            if (arr[child] < arr[parent]) {
                swap(arr, child, parent);
                parent = child;
                child = 2 * parent + 1;
            } else {
                break;
            }
        }
    }

    //入队
    public void offer(int value) {
        if (size == arr.length) {
            arr = Arrays.copyOf(arr, arr.length * 2);
        }
        arr[size++] = value;    //尾插
        shiftUp(arr, size, size - 1);   //向上调整
    }

    //出队
    public int poll() {
        if (size > 0) {
            int peek = arr[0];
            swap(arr, 0, size - 1);
            size--;
            shiftDown(arr, size, 0);
            return peek;
        }
        return -1;
    }

    //取队顶元素
    public int peek() {
        return arr[0];
    }

    //判空
    public boolean isEmpty() {
        return size == 0;
    }
}
