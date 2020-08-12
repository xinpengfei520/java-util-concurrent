package com.xpf.juc.controller;

import com.xpf.juc.atomic.*;
import com.xpf.juc.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Counter")
public class CounterTestController {

    /**
     * 计数器 统计
     */
    private Counter counter = new CounterBasic();

    /**
     * 限流 --- 超过一定的处理量，多出来的量 不处理
     */
    @RequestMapping("/findByUserId")
    public R findByUserId() {
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

        return R.ok();
    }

    public static void main(String[] args) throws InterruptedException {
//        final Counter counter = new CounterAtomic();
//        final Counter counter = new CounterBasic();
//        final Counter counter = new CounterCAS();
        final Counter counter = new CounterUnsafe();

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                long begin = System.nanoTime();
                for (int j = 0; j < 10000; j++) {
                    counter.increase(); // i++
                }
                System.out.println("done...运算时间： " + (System.nanoTime() - begin));
            }).start();
        }

        Thread.sleep(1000L);
        System.out.println("计数器最终结果: " + counter.get());
        // 预期结果应该 --- 20000
    }
}

