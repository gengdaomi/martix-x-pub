package com.martix.x.arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created By Andrew-Geng on 2020/11/9 11:26 下午
 * <p>
 * 最长连续序列
 * lc 128 hard
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * <p>
 *  
 * <p>
 * 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 * <p>
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 */
public class LongestArraySolution {

    public static void main(String[] args) {
        System.out.println(new LongestArraySolution().longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    }

    /**
     * * 由于O(n)时间复杂度的限制，我们就不能采取先排序后遍历的思路。
     * *
     * * 针对O(n)时间复杂度的实现思路：遍历nums[]数组，利用Map存储元素nums[i]的值以及其所在连续序列的长度，此时基本只有两种情况：
     * *
     * *  数组中出现过元素nums[i]-1或nums[i]+1，意味着当前元素可以归入左或右序列，那么此时假如左右序列的长度分别为left、right，那么显然加入nums[i]后，
     * *  这整段序列的长度为 1+left+right，而由于这一整段序列中，只可能在左右两端扩展，所以只需要更新左右两端的value值即可。
     * *  数组中未出现过元素nums[i]-1或nums[i]+1，意味着当前元素所在的连续序列就是自身（只有自己一个元素）。
     * * 但是！！总是有坑在等着我们跳：nums数组存在重复数字。
     * *
     * * 假设nums[] 数组为[2,4,3,3]，按照上面的逻辑，map（key->value）中2->1，4->1，当遇到3时，因为左右两端都存在，因此会直接更新2->2，4->2，再次遇到3，2->3，4->3，于是我们需要判断是否已经处理过3这个重复数字，方法就是每次处理的数字nums[i]，也在map中更新它的值，
     * * 并且在遍历的时候先判断nums[i].value是不是0，如果不是0，那么意味着前面我们处理过了，直接跳过就好。
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        int max = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(); //key 数组中的下标，value所在连续序列的长度

        for (int i : nums) {
            if (map.getOrDefault(i, 0) == 0) {
                int left = map.getOrDefault(i - 1, 0);
                int right = map.getOrDefault(i + 1, 0);

                map.put(i, 1 + left + right);
                if (left != 0) {
                    map.put(i - left, left + right + 1);
                }
                if (right != 0) {
                    map.put(i + right, left + right + 1);
                }

                max = max > left + right + 1 ? max : left + right + 1;
            }
        }
        return max;
    }

    /**
     * 哈希表+动态规划
     * <p>
     * 本题目要求时间复杂度为 O ( n ) O(n) O(n)，因此我们在遍历的过程中查找是否有与当前相连的数字需要用到哈希表，
     * 这样才能保证每次查找都是 O ( 1 ) O(1) O(1)复杂度，核心思想就是拿当前数字去找与其左右相连的数字集合看看能否组成一个更大的集合，
     * 并更新两端的最长值，过程中遇到哈希表中已存在的值就跳过，并且维护一个最大值用于返回
     * <p>
     * 复杂度分析
     * <p>
     * 时间复杂度： O ( n ) O(n) O(n)
     * <p>
     * 遍历一次。
     * <p>
     * 空间复杂度： O ( n ) O(n) O(n)
     * <p>
     * 哈希表额外空间 O ( n ) O(n) O(n)。
     *
     * @param nums
     * @return
     */
    public int longestConsecutive_1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                continue;
            }

            int left = map.getOrDefault(nums[i] - 1, 0);
            int right = map.getOrDefault(nums[i] + 1, 0);

            int len = left + right + 1;
            max = Math.max(max, len);

            map.put(nums[i], len);
            map.put(nums[i] - left, len);
            map.put(nums[i] + right, len);
        }
        return max;
    }

    /**
     * 以子序列的头 来计算整个子序列的长度
     * 动态规划
     *
     * @param nums
     * @return
     */
    public int longestConsecutive_2(int[] nums) {
        Set<Integer> set = new HashSet<>(); //通过set进行去重处理
        for (int num : nums) {
            set.add(num);
        }

        int result = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int curNum = num;
                int curLen = 1;

                while (set.contains(curNum + 1)) {
                    curNum++;
                    curLen++;
                }
                result = curLen > result ? curLen : result;
            }
        }

        return result;
    }

    /**
     * 以子序列的尾 来计算整个子序列的长度
     * 动态规划
     *
     * @param nums
     * @return
     */
    public int longestConsecutive_4(int[] nums) {
        Set<Integer> set = new HashSet<>(); //通过set进行去重处理
        for (int num : nums) {
            set.add(num);
        }

        int result = 0;
        for (int num : set) {
            if (!set.contains(num + 1)) {
                int curNum = num;
                int curLen = 1;

                while (set.contains(curNum - 1)) {
                    curNum--;
                    curLen++;
                }
                result = curLen > result ? curLen : result;
            }
        }

        return result;
    }


    /**
     * 使用并查集的思想比较简单，将相邻的数字合并起来，然后再遍历一遍记录最大值即可
     * <p>
     * 时间复杂度： O ( n ) O(n) O(n)
     * <p>
     * 遍历一次。
     * <p>
     * 空间复杂度： O ( n ) O(n) O(n)
     * <p>
     * 并查集额外空间 O ( n ) O(n) O(n)。
     */
    class UnionFind {
        Map<Integer, Integer> parents;

        public UnionFind(int[] arr) {
            parents = new HashMap<>();
            for (int i : arr) {
                parents.put(i, i);
            }
        }

        public Integer find(int x) {
            if (!parents.containsKey(x)) return null;
            int t = parents.get(x);
            if (x != t) parents.put(x, find(t));
            return parents.get(x);
        }

        public boolean union(int x, int y) {
            Integer rootX = find(x), rootY = find(y);
            if (rootX == null || rootY == null) return false;
            if (rootX.equals(rootY)) return false;
            parents.put(rootX, rootY);
            return true;
        }
    }

    public int longestConsecutive_3(int[] nums) {
        if (nums.length == 0) return 0;
        UnionFind u = new UnionFind(nums);
        for (int num : nums) {
            u.union(num, num + 1);
        }
        int max = 1;
        for (int num : nums) {
            max = Math.max(max, u.find(num) - num + 1);
        }
        return max;
    }
}
