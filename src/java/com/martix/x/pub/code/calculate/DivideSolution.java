package com.martix.x.pub.code.calculate;

/**
 * Created by Andrew-Geng on 1:04 上午 2021/7/9
 * 两数相除 lc 29
 * <p>
 * 给定两个整数，被除数dividend和除数divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数dividend除以除数divisor得到的商。
 * <p>
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * <p>
 * 示例1:
 * <p>
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 * 示例2:
 * <p>
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 * <p>
 * 提示：
 * <p>
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231, 231− 1]。本题中，如果除法结果溢出，则返回 231− 1。
 */
public class DivideSolution {

    public int divide(int dividend, int divisor) {
        if(divisor == -1 && dividend == Integer.MIN_VALUE){
            return Integer.MAX_VALUE; // 溢出
        }

        int sign = 1;

        if((dividend > 0 && divisor < 0)||(dividend < 0 && divisor > 0)) {
            sign = -1;
        }

        // 都改为负号是因为int 的范围是[2^31, 2^31-1]，如果a是-2^32，转为正数时将会溢出
        int a = dividend>0 ? -dividend : dividend;
        int b = divisor>0 ? -divisor : divisor;

        if(a > b){
            return 0;
        }

        int ans = div(a,b);

        return sign == -1 ? -ans : ans;
    }

    private int div(int a, int b) {
        if(a > b){
            return 0;
        }

        int count = 1;
        int tb = b;

        while(tb+tb >= a && tb+tb < 0){ // 溢出之后不再小于0
            tb += tb;
            count += count;
        }

        return count+div(a-tb,b);
    }



    public int divide_1(int dividend, int divisor) {
        // 当除数为1，直接返回被除数
        if (divisor == 1) {
            return dividend;
        }
        // 当除数为-1且被除数为Integer.MIN_VALUE时，将会溢出，返回Integer.MAX_VALUE
        if (divisor == -1 && dividend == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }

        // 把被除数与除数调整为正数,为防止被除数Integer.MIN_VALUE转换为正数会溢出，使用long类型保存参数
        if (dividend < 0 && divisor < 0) {
            return this.divide(-(long) dividend, -(long) divisor);
        } else if (dividend < 0 || divisor < 0) {
            return -this.divide(Math.abs((long) dividend), Math.abs((long) divisor));
        } else {
            return this.divide((long) dividend, (long) divisor);
        }
    }

    private int divide(long dividend, long divisor) {
        // 如果被除数小于除数，结果明显为0
        if (dividend < divisor) {
            return 0;
        }
        long sum = divisor; // 记录用了count个divisor的和
        int count = 1; // 使用了多少个divisor
        while (dividend >= sum) {
            // 每次翻倍
            sum <<= 1;
            count <<= 1;
        }

        // 此时dividend < sum
        sum >>>= 1;
        count >>>= 1;

        // 此时dividend >= sum
        // 将count个divisor从dividend消耗掉，剩下的还需要多少个divisor交由递归函数处理
        return count + divide(dividend - sum, divisor);
    }
}
