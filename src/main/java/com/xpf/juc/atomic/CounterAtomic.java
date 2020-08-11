package com.xpf.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterAtomic implements Counter {
    //volatile int i = 0;
    // JUC 包 -- 针对 基本数据类型 - 原子操作的封装类
    AtomicInteger i = new AtomicInteger(0);

    @Override
    public int increase() {
        return i.incrementAndGet(); // i++
    }

    @Override
    public int decrease() {
        return i.decrementAndGet(); // i--
    }

    @Override
    public int get() {
        return i.get();
    }
}
