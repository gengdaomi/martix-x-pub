package com.martix.x.pub.code.number;

/**
 * Created by Andrew-Geng on 1:00 上午 2021/7/4
 * 环形链表的约瑟夫问题
 * <p>
 * 描述
 * <p>
 * 编号为1 到n 的
 * n 个人围成一圈。从编号为1 的人开始报数，报到m 的人离开。
 * 下一个人继续从1 开始报数。n−1 轮结束以后，只剩下一个人，问最后留下的这个人编号是多少？
 * <p>
 * 示例1
 * <p>
 * 输入：
 * 5,2
 * 复制
 * 返回值：
 * 3
 * 复制
 * 说明：
 * 开始5个人 1，2，3，4，5 ，从1开始报数，1->1，2->2编号为2的人离开
 * 1，3，4，5，从3开始报数，3->1，4->2编号为4的人离开
 * 1，3，5，从5开始报数，5->1，1->2编号为1的人离开
 * 3，5，从3开始报数，3->1，5->2编号为5的人离开
 * 最后留下人的编号是3
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 参考剑指offer：圆圈中最后剩下的数字
 */
public class YuesefuSolution {

    public static void main(String[] args) {
        System.out.println(new YuesefuSolution().ysf(5, 2));
    }

    public int ysf(int n, int m) {
        if (m < 1 || n < 1) {
            return -1;
        }

        int last = 0;
        // i代表有目前有个人,最后一轮剩下2个人，所以从2开始反推
        for (int i = 2; i <= n; i++) {
            last = (last + m) % i;
        }

        return last + 1;
    }

    /**
     * 首先：
     * 长度为 n 的序列会先删除第 m % n 个元素，然后剩下一个长度为 n - 1 的序列；
     * 那么，我们可以递归地求解 f(n - 1, m)，就可以知道对于剩下的 n - 1 个元素，最终会留下第几个元素，我们设答案为 x = f(n - 1, m)。
     *
     * 由于我们删除了第 m % n 个元素，将序列的长度变为 n - 1。
     * 当我们知道了 f(n - 1, m) 对应的答案 x 之后，我们也就可以知道，长度为 n 的序列最后一个删除的元素，
     * 应当是从 m % n 开始数的第 x 个元素。因此有 f(n, m) = (m % n + x) % n = (m + x) % n
     *
     * 时间空间复杂度O(n)
     * @param n
     * @param m
     * @return
     */
    public int ysf_1(int n, int m) {
        if (n < 1 || m < 1) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        return (ysf_1(n - 1, m) + m - 1) % n + 1;
    }

}
