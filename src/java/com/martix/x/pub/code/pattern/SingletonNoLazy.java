package com.martix.x.pub.code.pattern;

/**
 * Created by Andrew-Geng on 10:34 上午 2021/6/26
 *
 * 饿汉式(线程安全，调用效率高，但是不能延时加载)
 *
 * * 优点：一上来就把单例对象创建出来了，要用的时候直接返回即可，这种可以说是单例模式中最简单的一种实现方式；
 * * 缺点：单例在还没有使用到的时候，初始化就已经完成了。也就是说，如果程序从头到位都没使用这个单例的话，单例的对象还是会创建。
 *       这就造成了不必要的资源浪费。所以不推荐这种实现方式。
 */
public class SingletonNoLazy {

    private static SingletonNoLazy singletonNoLazy = new SingletonNoLazy();

    private SingletonNoLazy(){}

    public static SingletonNoLazy getSingletonNoLazy(){
        return singletonNoLazy;
    }
}
