package com.xpf.juc.lock;


import com.xpf.juc.lock.runnable.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainActivity {

    public static void main(String[] args) {
        ordinaryNoLockTest();
        reentrantLockTest();
        objectLockTest();
        classLockTest();
        volatileTest();
        atomicTest();
    }

    private static void ordinaryNoLockTest() {
        try {
            OrdinaryRunnable runnable = new OrdinaryRunnable();

            Thread thread1 = new Thread(runnable);
            Thread thread2 = new Thread(runnable);

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();

            log.debug("### 普通的没有加锁的测试 ### i===" + OrdinaryRunnable.i);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void reentrantLockTest() {
        try {
            ReentrantLockRunnable runnable = new ReentrantLockRunnable();

            // 多个线程执行的是同一个对象，所以需要使用对象锁
            Thread thread1 = new Thread(runnable);
            Thread thread2 = new Thread(runnable);

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();

            log.debug("### ReentrantLock 锁测试 ### i===" + ReentrantLockRunnable.i);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void objectLockTest() {
        try {
            ObjectLockRunnable runnable = new ObjectLockRunnable();

            // 多个线程执行的是同一个对象，所以需要使用对象锁
            Thread thread1 = new Thread(runnable);
            Thread thread2 = new Thread(runnable);

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();

            log.debug("### 对象锁测试 ### i===" + ObjectLockRunnable.i);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void classLockTest() {
        try {
            ClassLockRunnable runnable1 = new ClassLockRunnable();
            ClassLockRunnable runnable2 = new ClassLockRunnable();

            // 多个线程执行的是相同类的不同对象，应该使用类锁
            Thread thread1 = new Thread(runnable1);
            Thread thread2 = new Thread(runnable2);

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();

            log.debug("### 类锁测试 ### i===" + ClassLockRunnable.i);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void volatileTest() {
        try {
            VolatileRunnable runnable = new VolatileRunnable();

            Thread thread1 = new Thread(runnable);
            Thread thread2 = new Thread(runnable);

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();

            log.debug("### Volatile 测试 ### i===" + VolatileRunnable.i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void atomicTest() {
        try {
            AtomicRunnable runnable = new AtomicRunnable();

            Thread thread1 = new Thread(runnable);
            Thread thread2 = new Thread(runnable);

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();

            log.debug("### Atomic 测试 ### i===" + AtomicRunnable.i.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
