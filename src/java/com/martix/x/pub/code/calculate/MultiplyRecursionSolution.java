package com.martix.x.pub.code.calculate;

/**
 * Created by Andrew-Geng on 21:25 2022/9/14
 * 递归乘法 lc 面试题 08.05.
 * <p>
 * 递归乘法。 写一个递归函数，不使用 * 运算符， 实现两个正整数的相乘。可以使用加号、减号、位移，但要吝啬一些。
 * <p>
 * 示例1:
 * <p>
 * 输入：A = 1, B = 10
 * 输出：10
 * 示例2:
 * <p>
 * 输入：A = 3, B = 4
 * 输出：12
 */
public class MultiplyRecursionSolution {

    public static void main(String[] args){
        int x=4,y=6;
        int result = new MultiplyRecursionSolution().multiply(x,y);
        System.out.println(result);
    }

    private int result = 0;

    /**
     * 递归解法
     * @param A
     * @param B
     * @return
     */
    public int multiply(int A, int B) {
        int min = A, max = B;

        if (A > B) {
            min = B;
            max = A;
        }
        recursion(min, max, 0);
        return result;
    }

    /**
     * >>位运算符右移,不会忽略符号位
     *
     * >>>无符号右移，忽略符号位，空位都以0补齐
     * @param min
     * @param max
     * @param i
     */
    private void recursion(int min, int max, int i) {
        if ((min >>> i) == 0){
            return;
        }
        if (((min >>> i) & 1) == 1) { //如果右移i为与上1等于1的话，说明最后一是0，说明这个位可以作为i来移动
            result += max << i;
        }

        recursion(min, max, i + 1);
    }

    /**
     * 非递归解法
     *
     * 1.首先，求得A和B的最小值和最大值;
     * 2.然后，可以对其中的最小值当做乘数（为什么选最小值，因为选最小值当乘数，可以算的少），将其拆分成2的幂的和，其实就是用二进制的视角去看待min；
     * 比如 12的二进制b表示就是1100，即1000+0100；
     * 举个例子：
     * 13 * 12 = 13 * (8 + 4) = 13 * 8 + 13 * 4 = (13 << 3) + (13 << 2)
     * @param A
     * @param B
     * @return
     */
    public int multiply_1(int A, int B) {
        int min = Math.min(A, B);
        int max = Math.max(A, B);
        int result = 0;

        for (int i = 0; min != 0; i++) {
            if ((min & 1) == 1) {
                result += max << i;
            }
            min >>= 1;
        }

        return result;
    }
}
