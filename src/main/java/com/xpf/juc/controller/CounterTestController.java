package com.xpf.juc.controller;

import com.xpf.juc.atomic.Counter;
import com.xpf.juc.atomic.CounterBasic;
import com.xpf.juc.atomic.CounterCAS;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterTestController {

    /**
     * 计数器 统计
     */
    private Counter counter = new CounterBasic();

    /**
     * 限流 --- 超过一定的处理量，多出来的量 不处理
     */
    @RequestMapping()
    public String findUserId() {
        // i++;
        counter.increase();
        try {
            if (counter.get() > 3000) {
                return null;
            }
            // TODO userService.find.... // 这段代码 同时调用的数量
        } finally {
            // 并发数减一
            counter.decrease();
        }
        return "xx";
    }

    public static void main(String[] args) throws InterruptedException {
        final Counter ct = new CounterCAS();

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                long begin = System.nanoTime();
                for (int j = 0; j < 10000; j++) {
                    ct.increase(); // i++
                }
                System.out.println("done...运算时间： " + (System.nanoTime() - begin));
            }).start();
        }

        Thread.sleep(6000L);
        System.out.println("计数器最终结果: " + ct.get());
        // 预期结果应该 --- 20000
    }
}

