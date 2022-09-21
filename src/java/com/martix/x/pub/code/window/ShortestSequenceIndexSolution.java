package com.martix.x.pub.code.window;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew-Geng on 00:23 2022/9/16
 * 最短超串 lc 面试17.18
 * <p>
 * 假设你有两个数组，一个长一个短，短的元素均不相同。找到长数组中包含短数组所有的元素的最短子数组，其出现顺序无关紧要。
 * 返回最短子数组的左端点和右端点，如有多个满足条件的子数组，返回左端点最小的一个。若不存在，返回空数组。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * big = [7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7]
 * small = [1,5,9]
 * <p>
 * 因为是其中子数组[5,7,9,1]
 * 输出: [7,10]
 * 示例 2:
 * <p>
 * 输入:
 * big = [1,2,3]
 * small = [4]
 * 输出: []
 */
public class ShortestSequenceIndexSolution {

    public static void main(String[] args){
        int[] s = new ShortestSequenceIndexSolution().shortestSeq(new int[]{7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7},new int[]{1,5,9});
        System.out.println(s);
    }

    /**
     * 滑动窗口+hashmap
     * <p>
     * 时间复杂度为O(n*m)
     *
     * @param big
     * @param small
     * @return
     */
    public int[] shortestSeq(int[] big, int[] small) {
        int[] res = {};//结果数组

        int smallLength = small.length;//用来维护map中当前含有且未出现在大数组里的数字个数
        int bigLength = big.length;
        int left = 0;
        int right = 0;
        int minLength = bigLength;//存放结果子串长度
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < smallLength; i++) {
            map.putIfAbsent(small[i], 1);//存放小数组所有元素，因为不会重复，直接赋为1
        }

        while (right < bigLength) {//边界条件
            if (map.containsKey(big[right])) {//判断right指针代表的元素是否出现在map

                if (map.get(big[right]) > 0) {//即使含有该元素，只有当map中该元素数量大于0才可以让smallLen--！！在该处浪费了很多时间，比如big：1123，small：123，1在big里出现两次，但只有第一次出现会被统计
                    smallLength--;
                }

                map.put(big[right], map.get(big[right]) - 1);//每次都要将map含有的big数组元素-1
            }

            while (smallLength == 0) {//big里找到了一个子串
                if (right - left < minLength) {
                    minLength = right - left;
                    res = new int[]{left, right};
                }
                if (map.containsKey(big[left])) {//对左指针判断
                    map.put(big[left], map.get(big[left]) + 1);//每次都要将map含有的big数组元素+1

                    if (map.get(big[left]) > 0) {//只有当map中该元素数量大于0才可以让smallLen++
                        smallLength++;
                    }
                }

                left++;
            }

            right++;
        }

        return res;

    }
}
