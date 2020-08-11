package com.xpf.juc.lock.runnable;

/**
 * Created by x-sir on 2019-05-26 :)
 * Function:类锁
 */
public class ClassLockRunnable implements Runnable {

    public static int i = 0;

    /**
     * 类锁，使用 static 修饰
     */
    private static synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 100000; j++) {
            increase();
        }
    }
}
