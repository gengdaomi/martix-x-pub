package com.martix.x.pub.code.number;

/**
 * Created by Andrew-Geng on 3:27 下午 2021/5/2
 * 旋转数字
 * lc 788
 * <p>
 * 我们称一个数 X 为好数, 如果它的每位数字逐个地被旋转 180 度后，我们仍可以得到一个有效的，且和 X 不同的数。要求每位数字都要被旋转。
 * <p>
 * 如果一个数的每位数字被旋转以后仍然还是一个数字，则这个数是有效的。0, 1, 和 8 被旋转后仍然是它们自己；
 * 2 和 5 可以互相旋转成对方（在这种情况下，它们以不同的方向旋转，换句话说，2 和 5 互为镜像）；
 * 6 和 9 同理，除了这些以外其他的数字旋转以后都不再是有效的数字。
 * <p>
 * 现在我们有一个正整数N, 计算从1 到N 中有多少个数X 是好数？
 *
 * <p>
 * 示例：
 * <p>
 * 输入: 10
 * 输出: 4
 * 解释:
 * 在[1, 10]中有四个好数： 2, 5, 6, 9。
 * 注意 1 和 10 不是好数, 因为他们在旋转之后不变。
 * <p>
 * 提示：
 * <p>
 * N的取值范围是[1, 10000]。
 */
public class RotateDigitsSolution {

    public static void main(String[] args) {
    }

    public int rotatedDigits(int N) {

        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (isGoodNum(i, false)) {
                count++;
            } else {
                continue;
            }
        }

        return count;
    }

    /**
     * 判断当前数字是否是 好 数
     *
     * @param num
     * @return
     */
    private boolean isGoodNum(int num, boolean flag) {
        if (num == 0) {
            return flag;
        }

        int d = num % 10;
        if (d == 3 || d == 4 || d == 7) { // 3 4 7这类数，旋转后就是无效数字
            return false;
        }
        if (d == 0 || d == 1 || d == 8) {
            return isGoodNum(num / 10, flag);
        }

        return isGoodNum(num / 10, true);
    }
}
