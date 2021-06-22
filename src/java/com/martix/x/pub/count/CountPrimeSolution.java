package com.martix.x.pub.count;

import java.util.Arrays;

/**
 * Created by Andrew-Geng on 10:19 上午 2021/4/16
 */
public class CountPrimeSolution {

    public static void main(String[] args) {
        System.out.println(new CountPrimeSolution().countPrime(7));
    }

    /**
     * 时间复杂度O(N^2)
     *
     * @param n
     * @return
     */
    public int countPrime(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {  //素数从2开始算
            if (isPrime(i)) {
                count++;
            }
        }

        return count;
    }

    private boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % 2 == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 时间复杂度O(NloglogN)
     * 由于乘法因子对称性，外层for循环只需要遍历[2,sqrt(n)]
     *
     *
     * @param n
     * @return
     */
    public int countPrime_1(int n) {
        boolean[] isPrime = new boolean[n]; //初始化
        Arrays.fill(isPrime, true);

        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }

        return count;
    }

}
