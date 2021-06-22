package com.martix.x.gather;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 集合操作
 * 数组去重 两数组交集 两数组并集
 */
public class GatherSolution {

    /**
     * 数组去重
     *
     * @param array
     * @return
     */
    public static int[] unique(int[] array) {
        int len = -1;
        if (array == null || (len = array.length) < 2) {
            return len == -1 ? null : Arrays.copyOf(array, len);
        }

        Map<Integer, Object> uniqueMap = new HashMap<>();
        for (int value : array) {
            uniqueMap.put(value, null);
        }

        int uniqueNums = uniqueMap.size();
        int[] uniqueArray = new int[uniqueNums];
        for (Integer key : uniqueMap.keySet()) {
            uniqueArray[--uniqueNums] = key;
        }

        Arrays.sort(uniqueArray);
        return uniqueArray;
    }

    /**
     * 求两个数组的交集
     *
     * @param aArray
     * @param bArray
     * @return
     */
    public static int[] intersect(int[] aArray, int[] bArray) {
        if (aArray == null || bArray == null) {
            return null;
        }

        int aLen = 0, bLen = 0;
        if ((aLen = aArray.length) == 0 || (bLen = bArray.length) == 0) {
            return new int[0];
        }

        Map<Integer, Boolean> intersectMap = new HashMap<>();
        for (int aValue : aArray) {
            intersectMap.put(aValue, true);
        }

        int intersectLen = (aLen > bLen) ? bLen : aLen;
        int[] intersectArray = new int[intersectLen];
        int iCount = 0;
        for (int bValue : bArray) {
            Boolean isAdd = intersectMap.get(bValue);
            if (isAdd != null && isAdd) { // Can only be added once.
                intersectArray[iCount++] = bValue;
                intersectMap.put(bValue, false);
            }
        }

        intersectArray = Arrays.copyOf(intersectArray, iCount);
        Arrays.sort(intersectArray);
        return intersectArray;
    }

    /**
     * 求两个数组的并集
     *
     * @param aArray
     * @param bArray
     * @return
     */
    public static int[] union(int[] aArray, int[] bArray) {
        if (aArray == null || aArray.length == 0) {
            return GatherSolution.unique(bArray);
        }
        if (bArray == null || bArray.length == 0) {
            return GatherSolution.unique(aArray);
        }

        Map<Integer, Object> unionMap = new HashMap<>();
        for (int aValue : aArray) {
            unionMap.put(aValue, null);
        }

        for (int bValue : bArray) {
            unionMap.put(bValue, null);
        }

        int unionLen = unionMap.size();
        int[] unionArray = new int[unionLen];
        for (int key : unionMap.keySet()) {
            unionArray[--unionLen] = key;
        }

        Arrays.sort(unionArray);
        return unionArray;
    }

    public static void main(String[] args) {
        int[] i = new int[]{13, 33, 2, 455, 2, 56, 13, 44};
        int[] r1 = unique(i);

        Arrays.stream(r1)
                .mapToObj(index -> Integer.toString(index))
                .forEach(System.out::println);
    }
}
