package com.martix.x.pub.pattern;

/**
 * Created by Andrew-Geng on 10:36 上午 2021/6/26
 * <p>
 * Double CheckLock实现单例,DCL也就是双重锁判断机制（由于JVM底层模型原因，偶尔会出问题，不建议使用）
 * <p>
 * <p>
 * <p>
 * <p>
 * * volatile修饰的变量是具有可见性的（即被一个线程修改后，其他线程立即可见）；使用volatile可以禁止指令重排，
 * 避免多线程的时候在虚拟机中运行的时候指令重排造成的空指针错误（）
 * <p>
 * * 为了防止new Singleton被执行多次，因此在new操作之前加上Synchronized 同步锁，锁住整个类（注意，这里不能使用对象锁）。
 * <p>
 * * 进入Synchronized 临界区以后，还要再做一次判空。因为当两个线程同时访问的时候，线程A构建完对象 在执行return的时候（这个时候A线程已经释放锁了），
 * 线程B也已经通过了最初的判空验证，不做第二次判空的话，线程B还是会再次构建instance对象。
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 1. volatile关键字不但可以防止指令重排，也可以保证线程访问的变量值是主内存中的最新值。有关volatile的详细原理，我在以后的漫画中会专门讲解。
 * <p>
 * <p>
 * 2.使用枚举实现的单例模式，不但可以防止利用反射强行构建单例对象，而且可以在枚举类对象被反序列化的时候，保证反序列的返回结果是同一对象。
 * <p>
 * <p>
 * 对于其他方式实现的单例模式，如果既想要做到可序列化，又想要反序列化为同一对象，则必须实现readResolve方法。
 */
public class SingletonDCL {

    private volatile static SingletonDCL singletonDCL;

    private SingletonDCL() {
    }

    public SingletonDCL getSingletonDCL() {
        if (singletonDCL == null) {
            synchronized (SingletonDCL.class) {
                if (singletonDCL == null) {
                    singletonDCL = new SingletonDCL();
                }
            }
        }

        return singletonDCL;
    }
}
