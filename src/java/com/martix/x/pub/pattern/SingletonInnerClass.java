package com.martix.x.pub.pattern;

/**
 * Created by Andrew-Geng on 10:39 上午 2021/6/26
 *
 * 静态内部类实现模式（线程安全，调用效率高，可以延时加载
 *
 * * 从外部无法访问静态内部类SingletonLoader，只有当调用Singleton.getInstance方法的时候，才能得到单例对象INSTANCE；
 *
 * * instance对象初始化的时机并不是在单例类Singleton被加载的时候，而是在调用getInstance方法，使得静态内部类SingletonLoader被加载的时候。
 *  因此这种实现方式是利用classloader的加载机制来实现懒加载，并保证构建单例的线程安全。
 *
 * * 还是无法防止外部通过反射的方式来重复构建对象；
 */
public class SingletonInnerClass {

    private SingletonInnerClass (){}

    private static class Instance{
        private static final SingletonInnerClass instance = new SingletonInnerClass();
    }

    public static SingletonInnerClass getInstance(){
        return Instance.instance;
    }

}
