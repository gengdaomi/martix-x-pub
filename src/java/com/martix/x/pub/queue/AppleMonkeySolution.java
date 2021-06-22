package com.martix.x.pub.queue;

/**
 * 共计9个苹果，有2只猴子，一个猴子每次拿2个苹果，一个猴子每次拿3个苹果，如果剩余的苹果不够猴子每次拿的数量，则2只猴子停止拿苹果
 * <p>
 * Created By Andrew-Geng on 2020/5/13 10:11 上午
 */
public class AppleMonkeySolution {

    public int appleCount = 9;

    public static void main(String[] args) {
        AppleMonkeySolution appleMonkeySolution = new AppleMonkeySolution();

        Thread monkey1 = new Thread(new Monkey(2,appleMonkeySolution));
        Thread monkey2 = new Thread(new Monkey(3,appleMonkeySolution));

        monkey1.start();
        monkey2.start();
    }
}

class Monkey implements Runnable {
    public int num;
    public AppleMonkeySolution appleMonkeySolution;

    Monkey(int num, AppleMonkeySolution appleMonkeySolution) {
        this.num = num;
        this.appleMonkeySolution = appleMonkeySolution;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (appleMonkeySolution) {
                if (appleMonkeySolution.appleCount - num < 0) {
                    break;
                }

                appleMonkeySolution.appleCount = appleMonkeySolution.appleCount - num;
                System.out.println("consumer: " + num + ", count: " + appleMonkeySolution.appleCount);
            }
        }
    }
}


