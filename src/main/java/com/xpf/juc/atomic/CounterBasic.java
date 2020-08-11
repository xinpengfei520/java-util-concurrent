package com.xpf.juc.atomic;

public class CounterBasic implements Counter {
    volatile int i = 0; // 本质是修改内存中某一个变量的值

    @Override
    public int increase() { // 累加 计数 --
//        return i++; // 数字记录并发的请求

        // i++; JVM 里边做了什么事情 JAVA 翻译 字节码含义
        // 1. 获取属性 i 的值 getfield #2 <com/tony/edu/juc/atomic/CounterBasic.i>
        int current = i;

        // 2. 计算 i+1  iadd
        int result = current + 1;

        // 3. 给 i 赋值 putfield #2 <com/tony/edu/juc/atomic/CounterBasic.i>
        if (current == i) { // CAS操作 --- 比较 成功
            i = result; // 赋值
        } else {
            // 失败
        }
        // 操作过程中 数据发生了变化
        return i;
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
