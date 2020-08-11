package com.xpf.juc.lock.runnable;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by x-sir on 2019-05-26 :)
 * Function:Atomic 原子类线程安全练习
 */
@Slf4j
public class AtomicRunnable implements Runnable {

    /**
     * 理解 volatile 关键字，保证可见性，但不保证原子性
     */
    public static volatile AtomicInteger i = new AtomicInteger(0);

    private void increase() {
        i.incrementAndGet();
    }

    @Override
    public void run() {
        for (int j = 0; j < 100000; j++) {
            increase();
        }
    }
}
