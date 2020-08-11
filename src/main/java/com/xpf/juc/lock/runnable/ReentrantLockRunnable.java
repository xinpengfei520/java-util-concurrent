package com.xpf.juc.lock.runnable;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by x-sir on 2019-05-26 :)
 * Function:ReentrantLock
 */
public class ReentrantLockRunnable implements Runnable {

    public static int i = 0;

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
