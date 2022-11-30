package com.martix.x.pub.code.arrays;

/**
 * Created by Andrew-Geng on 17:33 2022/11/30
 * 数组嵌套 lc565
 *
 * 索引从0开始长度为N的数组A，包含0到N - 1的所有整数。找到最大的集合S并返回其大小，其中 S[i] = {A[i], A[A[i]], A[A[A[i]]], ... }且遵守以下的规则。
 *
 * 假设选择索引为i的元素A[i]为S的第一个元素，S的下一个元素应该是A[A[i]]，之后是A[A[A[i]]]... 以此类推，不断添加直到S出现重复的元素。
 *
 *
 * 示例1:
 *
 * 输入: A = [5,4,0,3,1,6,2]
 * 输出: 4
 * 解释:
 * A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
 *
 * 其中一种最长的 S[K]:
 * S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
 *
 */
public class NestArraySolution {

    /**
     * 核心思路：原地标记
     *
     * 利用「nums 中的元素大小在[0,n−1] 之间」这一条件，
     * 我们可以省略vis 数组，改为标记nums[i]=n，来实现和vis 数组同样的功能
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    public int arrayNesting(int[] nums) {
        int result = 0, n = nums.length;

        for (int i = 0; i < n; i++) {
            int count = 0;

            while (nums[i] < n) {
                int num = nums[i];
                nums[i] = n;
                i = num;
                count++;
            }

            result = Math.max(result, count);
        }

        return result;
    }

    /**
     * 图
     *
     * 遍历数组，从i 向 nums[i] 连边，我们可以得到一张有向图。
     *
     * 由于题目保证nums 中不含有重复的元素，因此有向图中每个点的出度和入度均为1。
     *
     * 在这种情况下，有向图必然由一个或多个环组成。我们可以遍历nums，找到节点个数最大的环。
     *
     * 代码实现时需要用一个vis 数组来标记访问过的节点
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param nums
     * @return
     */
    public int arrayNesting_1(int[] nums) {
        int result = 0, n = nums.length;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            int count = 0;

            while (!visited[i]) {
                visited[i] = true;
                i = nums[i];
                count++;
            }

            result = Math.max(result, count);
        }

        return result;
    }
}
