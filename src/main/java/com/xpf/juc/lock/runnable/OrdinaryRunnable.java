package com.xpf.juc.lock.runnable;

/**
 * Created by x-sir on 2019-05-26 :)
 * Function:普通的不加锁的多线程
 */
public class OrdinaryRunnable implements Runnable {

    public static int i = 0;

    /**
     * 普通的不带锁的方法，会出现线程安全问题
     * 如果有多个线程访问并修改同一个对象，会导致对象变量的值不确定
     */
    private void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 100000; j++) {
            increase();
        }
    }
}
