package com.martix.x.pub.dymaic;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by Andrew-Geng on 2:03 上午 2021/7/4
 * <p>
 * 换钱的最少货币数
 * <p>
 * 给定数组arr，arr中所有的值都为正整数且不重复。每个值代表一种面值的货币，每种面值的货币可以使用任意张，
 * 再给定一个aim，代表要找的钱数，求组成aim的最少货币数。
 * 如果无解，请返回-1.
 * <p>
 * 要求：
 * 时间复杂度O（n）,空间复杂度O(n)
 * <p>
 * demo:
 * [5,2,3],20
 * 4
 * <p>
 * [3,5],2
 * -1
 */
public class MinMoneySolution {

    private int[] dp;

    /**
     * 动态规划:
     * <p>
     * dp[i]表示凑成面额i的需要的最少货币数
     * 然后枚举每个面额的货币，更新dp数组即可
     * dp[i]=min(dp[i],dp[i-arr[j]])
     *
     * 时间复杂度O(n*aim),空间复杂度O(n)
     * @param arr
     * @param aim
     * @return
     */
    public int minMoney(int[] arr, int aim) {
        dp = new int[aim + 1];
        return dp(arr, aim);
    }

    private int dp(int[] arr, int amount) {
        //base case  基本返回操作， 当金额等于的0 时候就不用返回了。
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }

        if (dp[amount] != 0) {// 返回某个金额的最少 金币数
            return dp[amount];
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            //遍历金币数组，每个可能都尝试
            int subProblem = dp(arr, amount - arr[i]);
            if (subProblem == -1) {// 无解跳过
                continue;
            }

            res = Math.min(subProblem + 1, res);
        }

        res = res == Integer.MAX_VALUE ? -1 : res;
        dp[amount] = res;

        return res;
    }

    private int res = Integer.MAX_VALUE;

    /**
     * 核心：
     * dfs 深度遍历
     *
     * @param arr
     * @param aim
     * @return
     */
    public int minMoney_1(int[] arr, int aim) {
        Arrays.sort(arr);
        dfs(arr, arr.length - 1, 0, aim);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public void dfs(int[] coins, int index, long count, int amount) {
        //剪枝，  下标小于0 ， 或者 比 res 大的没有必要统计
        if (index < 0 || count + amount / coins[index] >= res) {
            return;
        }
        // 计算
        if (amount % coins[index] == 0) {
            res = Math.min(res, (int) count + amount / coins[index]);
            return;
        }

        // 递归
        for (int i = amount / coins[index]; i >= 0; i--) {
            dfs(coins, index - 1, count + i, amount - i * coins[index]);
        }
    }

    /**
     * bfs
     *
     * @param coins
     * @param amount
     * @return
     */
    public int minMoney_3(int[] coins, int amount) {
        int count = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(amount);
        int[] table = new int[amount + 1];

        while (!deque.isEmpty()) {
            int len = deque.size();

            for (int i = 0; i < len; i++) {
                int temp = deque.pop();
                if (temp == 0) {
                    return count;
                }

                for (int coin : coins) {
                    if (temp >= coin && table[temp - coin] == 0) {
                        deque.add(temp - coin);
                        table[temp - coin] = 1;
                    }
                }
            }

            count += 1;
        }
        return -1;
    }

}
