package com.martix.x.pub.code.permutation;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrew-Geng on 22:57 2022/9/14
 * 字符串的排列
 * <p>
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * <p>
 * <p>
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * <p>
 * 示例:
 * <p>
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 */
public class PermuteStringSolution {

    /**
     * 回溯法
     * 对于一个长度为n 的字符串（假设字符互不重复），其排列方案数共有: n×(n−1)×(n−2)…×2×1；
     * <p>
     * 排列方案的生成：
     * <p>
     * 根据字符串排列的特点，考虑深度优先搜索所有排列方案。
     * 即通过字符交换，先固定第1 位字符（n 种情况）、再固定第2 位字符（n−1 种情况）、... 、最后固定第n 位字符（1 种情况）。
     * <p>
     * 重复排列方案与剪枝：
     * 当字符串存在重复字符时，排列方案中也存在重复的排列方案。
     * 为排除重复方案，需在固定某位字符时，保证 “每种字符只在此位固定一次” ，即遇到重复字符时不交换，直接跳过。
     * 从 DFS 角度看，此操作称为 “剪枝” 。
     * <p>
     * 递归解析：
     * 1.终止条件： 当 x = len(c) - 1 时，代表所有位已固定（最后一位只有1种情况），则将当前组合 c 转化为字符串并加入 res ，并返回；
     * 2.递推参数： 当前固定位 x ；
     * 3.递推工作： 初始化一个 Set ，用于排除重复的字符；将第 x 位字符与 i∈ [x, len(c)] 字符分别交换，并进入下层递归；
     * 3.1 剪枝： 若 c[i] 在 Set 中，代表其是重复字符，因此 “剪枝” ；
     * 3.2 将 c[i] 加入 Set ，以便之后遇到重复字符时剪枝；
     * 3.3 固定字符： 将字符 c[i] 和 c[x] 交换，即固定 c[i] 为当前位字符；
     * 3.4 开启下层递归： 调用 dfs(x + 1) ，即开始固定第 x + 1 个字符；
     * 3.5 还原交换： 将字符 c[i] 和 c[x] 交换（还原之前的交换）
     * <p>
     * <p>
     * 时间复杂度O(N!N) N为s字符串的长度；
     * 因为时间复杂度和字符串排列的方案数成线性关系，方案数为N×(N−1)×(N−2)…×2×1 ，即复杂度为O(N!) ；所以字符串拼接操作 join()
     * <p>
     * <p>
     * 空间复杂度O(N^2)
     *
     * @param s
     * @return
     */
    public String[] permutation(String s) {
        c = s.toCharArray();
        dfs(0);

        return result.toArray(new String[result.size()]);
    }

    void dfs(int x) {
        if (x == c.length - 1) {
            result.add(String.valueOf(c));      // 添加排列方案
            return;
        }

        Set<Character> set = new HashSet<>();
        for (int i = x; i < c.length; i++) {
            if (set.contains(c[i])){
                continue; // 重复，因此剪枝
            }

            set.add(c[i]);

            swap(i, x);                      // 交换，将 c[i] 固定在第 x 位
            dfs(x + 1);                      // 开启固定第 x + 1 位字符
            swap(i, x);                      // 恢复交换
        }
    }

    List<String> result = new LinkedList<>();
    char[] c;

    void swap(int a, int b) {
        char temp = c[a];
        c[a] = c[b];
        c[b] = temp;
    }

}
