package com.xpf.juc.atomic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class CounterCAS implements Counter {

    /**
     * 本质是修改内存中某一个变量的值
     */
    volatile int i = 0;

    private static Unsafe unsafe;

    /**
     * 字段偏移量(相对对象开始的位置) 01010 10101 0101 0101010 101
     */
    private static long valueOffset;

    static {
        try {
            // jvm --- 反射
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            // 暴力破解
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);

            Field fieldI = CounterCAS.class.getDeclaredField("i");
            valueOffset = unsafe.objectFieldOffset(fieldI);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int increase() {
        int current; // 内存当前值
        int result; // 计算后的结果

        do {
            // 1. 获取属性 i 的值
            current = i;
            // 2. 计算 i+1  i add
            result = current + 1;
            // 3. CAS 操作进行赋值
            // 多个线程同时调用，只有一个能调用成功
        } while (!unsafe.compareAndSwapInt(this, valueOffset, current, result));

        return result;
    }

    @Override
    public int decrease() {
        return i--;
    }

    @Override
    public int get() {
        return i;
    }
}
