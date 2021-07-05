package com.martix.x.pub.binary;

/**
 * Created by Andrew-Geng on 2:10 上午 2021/4/17
 * 爱吃香蕉的珂珂 (找到最小速度)
 * lc 875
 * <p>
 * 珂珂喜欢吃香蕉。这里有N堆香蕉，第 i 堆中有piles[i]根香蕉。警卫已经离开了，将在H小时后回来。
 * 珂珂可以决定她吃香蕉的速度K（单位：根/小时）。
 * 每个小时她将会选择一堆香蕉，从中吃掉 K 根。
 * 如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
 * <p>
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 * 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
 * <p>
 * 示例 1：
 * <p>
 * 输入: piles = [3,6,7,11], H = 8
 * 输出: 4
 * 示例2：
 * <p>
 * 输入: piles = [30,11,23,4,20], H = 5
 * 输出: 30
 * 示例3：
 * <p>
 * 输入: piles = [30,11,23,4,20], H = 6
 * 输出: 23
 */
public class KoKoEatingSpeedSolution {

    public static void main(String[] args) {
    }

    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = this.getMax(piles) + 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (canFinish(piles, mid, h)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    /**
     * 判断所有香蕉在以当前speed消化，是否能够在h消化完
     *
     * @param piles
     * @param speed
     * @param h
     * @return
     */
    private boolean canFinish(int[] piles, int speed, int h) {
        int time = 0;
        for (int i : piles) {
            time += time(i, speed);
        }

        return time <= h;
    }

    /**
     * 获取 按照speed 消化n个香蕉的时间是多少 ，非整数部分 向上取整
     *
     * @param n
     * @param speed
     * @return
     */
    private int time(int n, int speed) {
        return (n / speed) + (n % speed > 0 ? 1 : 0);
    }

    private int getMax(int[] piles) {
        int result = 0;
        for (int speed : piles) {
            result = Math.max(result, speed);
        }

        return result;
    }
}
