package com.martix.x.pub.code.dymaic;

/**
 * 买卖股票的最佳时机 lc 121
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 注意：你不能在买入股票前卖出股票。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * Created By Andrew-Geng on 2020/5/16 12:42 上午
 */
public class StockSailOnceSolution {

    public static void main(String[] args){
        System.out.println(new StockSailOnceSolution().maxProfit(new int[]{7,1,5,3,6,4}));
    }

    /**
     * 思路
     * <p>
     * 将第一个数看做最小值，与后面比它大的数求差
     * 当遇到比最小值小的数时，替换最小值
     * 比较每次求取的差值，取较大的
     *
     *
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int max = 0;
        int min = Integer.MAX_VALUE;

        for (int price : prices) {
            if (min > price) {
                min = price;
            } else if (price - min > max) {
                max = price - min;
            }
        }

        return max;
    }
}
