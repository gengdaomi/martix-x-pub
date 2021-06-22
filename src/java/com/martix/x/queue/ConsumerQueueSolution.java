package com.martix.x.queue;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * //评测题目: 有两个线程A,B，线程A负责向队列Q中不停的写入数据，线程B负责从队列Q不停的读取数据并输出，
 * //要求两个线程并发执行。请实现此队列，并写使用的示例代码
 * //要求：
 * //1、不允许使用jdk现有的任何Queue实现类
 * //2、请勿查询在线资料。
 */
public class ConsumerQueueSolution {

    static ConsumerQueue<String> consumerQueue = new ConsumerQueue<>();
    static String waitNode = new String();

    /**
     * 自定义的生产者消费者对列
     *
     * @param <T>
     */
    static class ConsumerQueue<T> {
        private LinkedList<T> queue = new LinkedList<T>();

        /**
         * @param e
         */
        public synchronized void push(T e) {
            queue.addLast(e);
        }

        /**
         * @return
         */
        public T peek() {
            T t = null;
            try {
                t = queue.getFirst();
            } catch (NoSuchElementException e) {
            }
            return t;
        }

        /**
         *
         */
        public void pop() {
            queue.removeFirst();
        }

        /**
         * @return
         */
        public boolean empty() {
            return queue.isEmpty();
        }

        /**
         * @return
         */
        public int size() {
            return queue.size();
        }
    }

    /**
     * 生产者
     */
    static class Provider extends Thread {

        @Override
        public void run() {
            while (true) {
                System.out.println("push data！");
                consumerQueue.push("meta");

                synchronized (waitNode) {
                    waitNode.notifyAll();
                }

                if (consumerQueue.size() >= 10) {
                    System.out.println("data is full!");

                    synchronized (waitNode) {
                        try {
                            waitNode.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                try {
                    Thread.sleep(400L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static Runnable providerRunnable = () -> {
        while (true) {
            System.out.println("push data！");
            consumerQueue.push("meta");

            synchronized (waitNode) {
                waitNode.notifyAll();
            }

            if (consumerQueue.size() >= 10) {
                System.out.println("data is full!");

                synchronized (waitNode) {
                    try {
                        waitNode.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                Thread.sleep(400L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 消费者
     */
    static class Consumer extends Thread {

        @Override
        public synchronized void run() {
            while (true) {
                System.out.println("load data！");
                String a = consumerQueue.peek();

                synchronized (waitNode) {
                    waitNode.notifyAll();
                }
                if (a == null) {
                    System.out.println("no data!");

                    synchronized (waitNode) {
                        try {
                            waitNode.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }


                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static Runnable consumerRunnable = () -> {
        while (true) {
            System.out.println("load data！");
            String a = consumerQueue.peek();

            synchronized (waitNode) {
                waitNode.notifyAll();
            }
            if (a == null) {
                System.out.println("no data!");

                synchronized (waitNode) {
                    try {
                        waitNode.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }


            try {
                Thread.sleep(600L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    public static void main(String[] args) {
//        Provider provider = new Provider();
//        Consumer consumer = new Consumer();
//
//
//        provider.start();
//        consumer.start();

        new Thread(providerRunnable).start();
        new Thread(consumerRunnable).start();
    }
}
