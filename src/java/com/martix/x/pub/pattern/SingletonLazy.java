package com.martix.x.pub.pattern;

/**
 * Created by Andrew-Geng on 10:35 上午 2021/6/26
 * <p>
 * 懒汉式(线程安全，调用效率不高，但是能延时加载)
 * <p>
 * getInstance 加锁synchronized的原因：防止多个线程 同时判断到instance==null,并都去new一个对象出来
 */
public class SingletonLazy {

    //类初始化时，不初始化这个对象(延时加载，真正用的时候再创建)
    private static SingletonLazy instance;

    private SingletonLazy() {
    }

    //方法同步，调用效率低
    public static synchronized SingletonLazy getInstance() {
        if (instance == null) {
            instance = new SingletonLazy();
        }

        return instance;
    }
}
