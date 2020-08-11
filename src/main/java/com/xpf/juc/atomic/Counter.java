package com.xpf.juc.atomic;

/**
 * 计数器
 */
public interface Counter {
    /**
     * 加1
     */
    int increase();

    /**
     * 减1
     */
    int decrease();

    /**
     * 返回当前值
     */
    int get();
}
