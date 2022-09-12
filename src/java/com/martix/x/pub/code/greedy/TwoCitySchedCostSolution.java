package com.martix.x.pub.code.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Andrew-Geng on 1:22 上午 2021/7/4
 *
 * 两地调度 lc 1029
 *
 * 公司计划面试 2n 人。给你一个数组 costs ，其中 costs[i] = [aCosti, bCosti] 。第 i 人飞往 a 市的费用为 aCosti ，飞往 b 市的费用为 bCosti 。
 *
 * 返回将每个人都飞到 a 、b 中某座城市的最低费用，要求每个城市都有 n 人抵达。
 *
 *
 * 示例 1：
 *
 * 输入：costs = [[10,20],[30,200],[400,50],[30,20]]
 * 输出：110
 * 解释：
 * 第一个人去 a 市，费用为 10。
 * 第二个人去 a 市，费用为 30。
 * 第三个人去 b 市，费用为 50。
 * 第四个人去 b 市，费用为 20。
 *
 * 最低总费用为 10 + 30 + 50 + 20 = 110，每个城市都有一半的人在面试。
 * 示例 2：
 *
 * 输入：costs = [[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]]
 * 输出：1859
 * 示例 3：
 *
 * 输入：costs = [[515,563],[451,713],[537,709],[343,819],[855,779],[457,60],[650,359],[631,42]]
 * 输出：3086
 *
 */
public class TwoCitySchedCostSolution {

    public static void main(String[] args){
        int[][]costs = new int[][]{{259,770},{448,54},{926,667},{184,139},{840,118},{577,469}};
        int result = new TwoCitySchedCostSolution().twoCitySchedCost_1(costs);
        System.out.println(result);
    }

    /**
     * 核心思路：
     * 贪心
     * 我们这么来看问题；公司首先将这2N 个人全都安排飞往B 市，再选N 个人改变它们的行程，让他们飞往A 市。
     * 如果选择改变一个人的行程，那么公司将会额外付出 price_A - price_B 的费用，这个费用可正可负;
     *
     * 因此最优的方案是，选出 price_A - price_B 最小的
     * N 个人，让他们飞往 A 市，其余人飞往 B 市;
     *
     * 算法：
     * 按照 price_A - price_B 从小到大排序；
     * 将前N 个人飞往 A 市，其余人飞往 B 市，并计算出总费用
     *
     * 时间复杂度O(logN)，因为需要对其排序
     * 空间复杂度O(1)
     * @param costs
     * @return
     */
    public int twoCitySchedCost(int[][] costs) {
        // Sort by a gain which company has
        // by sending a person to city A and not to city B
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o1[1] - (o2[0] - o2[1]);
            }
        });

        int total = 0;
        int n = costs.length / 2;
        // To optimize the company expenses,
        // send the first n persons to the city A
        // and the others to the city B
        for (int i = 0; i < n; ++i){
            total += costs[i][0] + costs[i + n][1];
        }

        return total;
    }

    /**
     *核心思路：
     * 1.先假设全部去A城市
     * 2.用一个数组保存每个人（去b城市的费用-去a城市的费用）
     * 3.按从小到大的顺序排序，给费用差数组排序
     * 4。将费用差最小的一半数组的值，加给总费用
     * @param costs
     * @return
     */
    public int twoCitySchedCost_1(int[][] costs) {
        int result = 0;
        int[] temp = new int[costs.length];

        for (int i = 0; i < costs.length; i++) {
            temp[i] = costs[i][1] - costs[i][0];
            result += costs[i][0];
        }

        Arrays.sort(temp);

        for (int i = 0; i < temp.length/2; i++) {
            result += temp[i];
        }

        return result;
    }
}
