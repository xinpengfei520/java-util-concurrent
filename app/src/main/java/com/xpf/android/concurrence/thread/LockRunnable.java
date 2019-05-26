package com.xpf.android.concurrence.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by x-sir on 2019-05-26 :)
 * Function:
 */
public class LockRunnable implements Runnable {

    static int i = 0;
    /**
     * 关于 synchronized & ReentrantLock 锁的区别
     * 1.在资源竞争不是很激烈的情况下，synchronized 的性能要优于 ReentrantLock
     *   但是在资源竞争很激烈的情况下，synchronized 的性能会下降十倍，但是 ReentrantLock 性能维持常态
     * 2.ReentrantLock 提供了多样化的同步，比如有时间限制同步，可以被 Interrupt 的同步，而 synchronized 不行
     *   在资源竞争不是很激烈的情况下，ReentrantLock 的性能稍微比 synchronized 差一点点，但是在资源竞争很激烈的
     *   情况下，synchronized 的性能会下降十倍，但是 ReentrantLock 性能维持常态
     */
    private ReentrantLock lock = new ReentrantLock();

    private void increaseWithLock() {
        lock.lock();
        try {
            i++;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 对象锁，使用对象才能调用这个方法
     * synchronized 修饰对象方法代表给对象枷锁
     * synchronized 修饰类方法（static）代表给类枷锁
     * 使用 synchronized 关键字修饰可以保证在多线程的情况下，访问同一个对象，使变量的值保持同步
     * 如果是不同的线程修改访问不同的对象，也会有线程安全问题，因为修改的是同一个变量 i, 而这个 i 是
     * static 类型的，也就是说它是属于类变量，因此，此时需要类锁来保证数据的同步
     */
    private synchronized void increase() {
        i++;
    }

    /**
     * 类锁，使用 static 修饰
     */
    private static synchronized void increaseWithStatic() {
        i++;
    }

    /**
     * 普通的不带锁的方法，会出现线程安全问题
     * 如果有多个线程访问并修改同一个对象，会导致对象变量的值不确定
     */
    private void increaseNoSync() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 100000; j++) {
            //increaseNoSync();
            //increase();
            //increaseWithStatic();
            increaseWithLock();
        }
    }
}
