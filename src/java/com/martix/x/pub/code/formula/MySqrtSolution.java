package com.martix.x.pub.code.formula;

/**
 * Created by Andrew-Geng on 11:02 2022/9/12
 * x 的平方根
 * <p>
 * 给你一个非负整数 x ，计算并返回x的 算术平方根 。
 * <p>
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * <p>
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 * <p>
 * 输入：x = 4
 * 输出：2
 * <p>
 * 输入：x = 8
 * 输出：2
 * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 */
public class MySqrtSolution {

    public static void main(String[] args){
        MySqrtSolution mySqrtSolution = new MySqrtSolution();
        int result = mySqrtSolution.mySqrt1(25);
        System.out.println(result);
    }

    /**
     * 二分查找
     * <p>
     * 由于x的平方根整数部分result满足k^2<=x的最大k值，因此对k进行二分查找来得到答案；
     * <p>
     * 二分查找的下界为 0，上界可以粗略地设定为x。
     * 在二分查找的每一步中，我们只需要比较中间元素 mid 的平方与x 的大小关系，并通过比较的结果调整上下界的范围。
     * 由于我们所有的运算都是整数运算，不会存在误差，因此在得到最终的答案 result 后，也就不需要再去尝试 result+1 了
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        int left = 0, right = x, result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if ((long) mid * mid <= x) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    /**
     * 通过公式 2r=r+x/r ，不断的循环迭代 r的平方=x
     *
     * 这个通过不断的丢精度的方式，来处理
     * @param x
     * @return
     */
    public int mySqrt1(int x) {
        long r = x;

        while (r * r > x) {
            r = (r + x / r) / 2;
        }

        return (int) r;
    }

    /**
     * 通过二分的方式来处理
     * 返回值，如果要降低精度，向下降低 则选择mid-1 (如2.333 取2),  向上降低则选择 mid+1 (如2.333 取3)
     * @param x
     * @return
     */
    public int mySqrt2(int x) {
        if (x <= 1) {
            return x;
        }

        int left = 1, right = x;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int sqrt = x / mid;

            if (sqrt == mid) {
                return mid;
            } else if (sqrt < mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }
}
