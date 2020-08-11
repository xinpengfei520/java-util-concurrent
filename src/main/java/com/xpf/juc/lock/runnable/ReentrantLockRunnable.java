package com.xpf.juc.lock.runnable;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by x-sir on 2019-05-26 :)
 * Function:ReentrantLock
 */
public class ReentrantLockRunnable implements Runnable {

    public static int i = 0;
    /**
     * 关于 synchronized & ReentrantLock 锁的区别
     * 1.在资源竞争不是很激烈的情况下，synchronized 的性能要优于 ReentrantLock
     * 但是在资源竞争很激烈的情况下，synchronized 的性能会下降十倍，但是 ReentrantLock 性能维持常态
     * 2.ReentrantLock 提供了多样化的同步，比如有时间限制同步，可以被 Interrupt 的同步，而 synchronized 不行
     * 在资源竞争不是很激烈的情况下，ReentrantLock 的性能稍微比 synchronized 差一点点，但是在资源竞争很激烈的
     * 情况下，synchronized 的性能会下降十倍，但是 ReentrantLock 性能维持常态
     */
    private ReentrantLock lock = new ReentrantLock();

    private void increase() {
        lock.lock();
        try {
            i++;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        for (int j = 0; j < 100000; j++) {
            increase();
        }
    }
}
