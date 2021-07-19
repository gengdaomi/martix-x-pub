package com.martix.x.pub.number;

/**
 * Created by Andrew-Geng on 11:26 下午 2021/7/19
 * <p>
 * 射击运动员10枪打90环的打法有多少种？
 * 用一段程序实现，将每种打法打印出来。（每法成绩均为整数，且在0到10环之间，可为0环也可为10环）。
 * <p>
 * 思路：使用递归思想
 * <p>
 * 首先考虑到一共要打十枪。则可以分三种情况来考虑：
 * <p>
 * 1.如果当前超过了十枪或者积分超过了给定值，则返回
 * <p>
 * 2.如果当前为第十枪，判断最后一枪的积分是否有可能达到给定值（0~10）
 * 如果可以，可能值加1，然后返回；否则直接返回。
 * <p>
 * 3.其他情况下，继续递归
 */
public class Shoot90RoundSolution {

    private static int sum = 0;
    private static int SCORE = 90;

    public static void main(String[] args) {

        compute(10, 0);
        System.out.println(sum);

    }

    public static void compute(int num, int scores) {
        if (num <= 0 || scores > SCORE) {
            return;
        }

        if (num == 1) {
            if (scores + 10 >= SCORE) {
                sum++;
                return;
            }
        }

        for (int i = 0; i <= 10; i++) {
            compute(num - 1, scores + i);
        }

    }
}
