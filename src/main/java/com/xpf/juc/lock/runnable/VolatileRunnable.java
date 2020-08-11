package com.xpf.juc.lock.runnable;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by x-sir on 2019-05-26 :)
 * Function:volatile 关键字线程安全练习
 */
@Slf4j
public class VolatileRunnable implements Runnable {

    /**
     * 理解 volatile 关键字，保证可见性，但不保证原子性
     */
    public static volatile int i = 0;

    private void increase() {
        synchronized (this) {
            i++;
        }
    }

    @Override
    public void run() {
        for (int j = 0; j < 100000; j++) {
            increase();
        }
    }
}
