package com.martix.x.pub.pattern;

/**
 * Created by Andrew-Geng on 10:41 上午 2021/6/26
 *
 * 枚举类（线程安全，调用效率高，不能延时加载，可以天然的防止反射和反序列化调用）
 */
public enum SingletonEnum {

    INSTANCE;

    public void singletonOperation(){
        //业务操作
    }
}
