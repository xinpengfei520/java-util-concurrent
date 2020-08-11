package com.xpf.juc.lock.runnable;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by x-sir on 2019-05-26 :)
 * Function:对象锁
 * 对象锁，使用对象才能调用这个方法
 * synchronized 修饰对象方法代表给对象枷锁
 * synchronized 修饰类方法（static）代表给类枷锁
 * 使用 synchronized 关键字修饰可以保证在多线程的情况下，访问同一个对象，使变量的值保持同步
 * 如果是不同的线程修改访问不同的对象，也会有线程安全问题，因为修改的是同一个变量 i, 而这个 i 是
 * static 类型的，也就是说它是属于类变量，因此，此时需要类锁来保证数据的同步
 */
public class ObjectLockRunnable implements Runnable {

    public static int i = 0;

    private synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 100000; j++) {
            increase();
        }
    }
}
