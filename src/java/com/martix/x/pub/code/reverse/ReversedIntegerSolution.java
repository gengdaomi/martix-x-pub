package com.martix.x.pub.code.reverse;

/**
 * Created By Andrew-Geng on 2020/5/14 11:32 下午
 * 反转整形数字
 * <p>
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * <p>
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * <p>
 * lc 7
 */
public class ReversedIntegerSolution {

    public static void main(String[] args) {
        //-2147483648
        System.out.println(new ReversedIntegerSolution().reverse(-2147483648));
    }

    public int reverse(int x) {
        long result = 0;

        while (x != 0) {
            result = result * 10 + x % 10;
            x /= 10;
        }

        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            result = 0;
        }

        return (int) result;
    }

    /**
     * 转换为字符串，通过左右指针的方式替换
     * <p>
     * 就像计数器的内容到最大值时会自动归零一样。而在整数中最小值为
     * -2147483648，所以当整数 x 的值最大时，加上 1 就会变成最小值-2147483648，也就
     * 是产生了溢出。
     *
     * @param x
     * @return
     */
    public int reverse_1(int x) {
        boolean isNegative = x <= 0 ? true : false;
        String s = Long.toString(isNegative ? -1l * x : x * 1l); //乘以-1l 为的是防止-2147483648 溢出


        int left = 0, right = s.length() - 1;
        StringBuffer sb = new StringBuffer(s);
        while (left < right) {
            char temp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, temp);

            left++;
            right--;
        }


        if (Long.parseLong(sb.toString()) > Integer.MAX_VALUE) {
            return 0;
        }

        return isNegative ? -Integer.parseInt(sb.toString()) : Integer.parseInt(sb.toString());
    }
}
