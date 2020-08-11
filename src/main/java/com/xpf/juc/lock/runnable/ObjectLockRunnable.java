package com.xpf.juc.lock.runnable;

/**
 * Created by x-sir on 2019-05-26 :)
 * Function:对象锁
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
