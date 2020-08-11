package com.xpf.juc.atomic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class CounterUnsafe implements Counter {
    volatile int i = 0;

    private static Unsafe unsafe = null;

    //i字段的偏移量
    private static long valueOffset;

    static {
        //unsafe = Unsafe.getUnsafe();
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);

            Field fieldi = CounterUnsafe.class.getDeclaredField("i");
            valueOffset = unsafe.objectFieldOffset(fieldi);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int increase() {
        //i++;
        for (; ; ) {
            int current = unsafe.getIntVolatile(this, valueOffset);
            int update = current + 1;
            if (unsafe.compareAndSwapInt(this, valueOffset, current, update)) {
                return update;
            }
        }
    }

    @Override
    public int decrease() {
        //i--;
        for (; ; ) {
            int current = unsafe.getIntVolatile(this, valueOffset);
            int update = current - 1;
            if (unsafe.compareAndSwapInt(this, valueOffset, current, update)) {
                return update;
            }
        }
    }

    @Override
    public int get() {
        return unsafe.getIntVolatile(this, valueOffset);
    }

}
