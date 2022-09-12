package com.martix.x.pub.code.thread;

import java.util.concurrent.Semaphore;

public class ZeroEvenOdd {

    private int n; //个数
    private Semaphore zero;
    private Semaphore even;
    private Semaphore odd;

    static PrintNumber printNumber = new PrintNumber();

    // 构造函数
    public ZeroEvenOdd(int n) {
        this.n = n;

        zero = new Semaphore(1);
        even = new Semaphore(0);
        odd = new Semaphore(0);
    }

    // 仅打印出 0
    public void zero(PrintNumber printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            zero.acquire();
            printNumber.sb.append(0);
            if ((i & 1) == 0) {
                even.release();
            } else {
                odd.release();
            }
        }
    }

    // 仅打印出 偶数
    public void even(PrintNumber printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            even.acquire();
            printNumber.sb.append(i);
            zero.release();
        }
    }

    // 仅打印出 奇数
    public void odd(PrintNumber printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            odd.acquire();
            printNumber.sb.append(i);
            zero.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(6);
        Thread t1 = new Thread(() -> {
            try {
                zeroEvenOdd.zero(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                zeroEvenOdd.even(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                zeroEvenOdd.odd(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t3.start();
        System.out.println(printNumber.sb.toString());
    }

    static class PrintNumber {
        StringBuffer sb = new StringBuffer();
    }
}
