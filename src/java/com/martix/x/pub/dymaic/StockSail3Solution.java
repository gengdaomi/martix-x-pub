package com.martix.x.pub.dymaic;

/**
 * Created by Andrew-Geng on 1:20 上午 2021/5/25
 * <p>
 * 买卖股票的最佳时机 III  lc 123
 * <p>
 * hard
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成两笔交易。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * <p>
 * 示例1:
 * <p>
 * 输入：prices = [3,3,5,0,0,3,1,4]
 * 输出：6
 * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * 示例 2：
 * <p>
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3：
 * <p>
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
 * 示例 4：
 * <p>
 * 输入：prices = [1]
 * 输出：0
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 105
 */
public class StockSail3Solution {

    private static int MAX_DEAL_TIMES = 2; //最大买卖次数

    /**
     * https://mp.weixin.qq.com/s/ojDSprv8U2Z0TqfVzwvYPg
     * <p>
     * 我们把股票的交易阶段设为变量m（用从0到4的数值表示），把天数范围设为变量n。而我们求解的最大收益，受这两个变量影响，用函数表示如下：
     * <p>
     * 最大收益 = F（n，m）（n>=1，0<=m<=4）
     * <p>
     * 动态规划的思路：
     * <p>
     * F（n，0） = 0
     * F（n，1）=  max（-price[n-1]，F（n-1，1））
     * F（n，2）=  max（F（n-1，1）+ price[n-1]，F（n-1，2））
     * F（n，3）=  max（F（n-1，2）- price[n-1]，F（n-1，3））
     * F（n，4）=  max（F（n-1，3）+ price[n-1]，F（n-1，4））
     * <p>
     * F（n，m） = max（F（n-1，m-1）+ price[n-1]，F（n-1，m））
     * <p>
     * 时间 空间复杂度O(n)
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        //表格的最大行数
        int n = prices.length;
        //表格的最大列数
        int m = MAX_DEAL_TIMES * 2 + 1;
        //使用二维数组记录数据
        int[][] resultTable = new int[n][m];
        //填充初始状态
        resultTable[0][1] = -prices[0];
        resultTable[0][3] = -prices[0];
        //自底向上，填充数据
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < m; j++) {

                if ((j & 1) == 1) { //判断是否是偶数
                    resultTable[i][j] = Math.max(resultTable[i - 1][j], resultTable[i - 1][j - 1] - prices[i]);
                } else {
                    resultTable[i][j] = Math.max(resultTable[i - 1][j], resultTable[i - 1][j - 1] + prices[i]);
                }
            }
        }
        //返回最终结果
        return resultTable[n - 1][m - 1];
    }

    /**
     * 同样的方式，但是复杂度降低为O(1)
     *
     * @param prices
     * @return
     */
    public int maxProfit_1(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        //表格的最大行数
        int n = prices.length;
        //表格的最大列数
        int m = MAX_DEAL_TIMES * 2 + 1;
        //使用一维数组记录数据
        int[] resultTable = new int[m];
        //填充初始状态
        resultTable[1] = -prices[0];
        resultTable[3] = -prices[0];
        //自底向上，填充数据
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < m; j++) {
                if ((j & 1) == 1) {
                    resultTable[j] = Math.max(resultTable[j], resultTable[j - 1] - prices[i]);
                } else {
                    resultTable[j] = Math.max(resultTable[j], resultTable[j - 1] + prices[i]);
                }
            }
        }
        //返回最终结果
        return resultTable[m - 1];
    }

    /**
     * 这个是一个比较泛化的解法，即 k次买入卖出
     *
     * @param prices
     * @param k
     * @return
     */
    public static int maxProfitForKTime(int[] prices, int k) {
        if (prices == null || prices.length == 0 || k <= 0) {
            return 0;
        }
        //表格的最大行数
        int n = prices.length;
        //表格的最大列数
        int m = k * 2 + 1;
        //使用一维数组记录数据
        int[] resultTable = new int[m];
        //填充初始状态
        for (int i = 1; i < m; i += 2) {
            resultTable[i] = -prices[0];
        }
        //自底向上，填充数据
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if ((j & 1) == 1) {
                    resultTable[j] = Math.max(resultTable[j], resultTable[j - 1] - prices[i]);
                } else {
                    resultTable[j] = Math.max(resultTable[j], resultTable[j - 1] + prices[i]);
                }
            }
        }
        //返回最终结果
        return resultTable[m - 1];
    }

}
