package com.xpf.juc.lock;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by x-sir on 2019-05-26 :)
 * Function:volatile 关键字线程安全练习
 */
@Slf4j
public class VolatileSafe {

    /**
     * 理解 volatile 关键字，保证可见性，但不保证原子性
     */
    private volatile boolean i = false;

    public VolatileSafe() {
    }

    public void increase() {
        i = true;
    }

    public void print() {
        while (!i) {
            log.debug("i:" + i);
        }
    }

}
