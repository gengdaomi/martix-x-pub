package com.martix.x.pub.code.validate;

/**
 * 判断101-200之间有多少个素数，并输出所有素数。
 * <p>
 * 程序分析：判断素数的方法：用一个数分别去除2到sqrt(这个数)，
 * 如果能被整除， 则表明此数不是素数，反之是素数
 */
public class PrimeValidateSolution {

    public static void main(String[] args) {
        int count = 0;

        for (int i = 101; i < 201; i++) {
            if (isPrime(i)) {
                count++;
                System.out.println(i);
            }
        }

        System.out.println("总数：" + count);
    }

    /**
     * 判断一个数是否为素数
     *
     * @param n
     * @return
     */
    public static boolean isPrime(int n) {
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % 2 == 0) {
                return false;
            }
        }
        return true;
    }
}
