package com.xpf.juc.controller;

import com.xpf.juc.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/Tomcat")
public class TomcatTestController {

    /**
     * Tomcat 线程池大小 100，最小空闲线程 10，创建的线程总数是 100 个
     * 如果并发数大于线程池大小，会等待其他请求释放线程资源，线程池中的线程是重复利用的
     * 最大线程数应该根据实际的服务器配置和业务场景和大量的压力测试来设置，已达到最优
     * 如果最大线程数设置的过大，会影响处理的速度，因为线程切换回消耗资源，如果过小，资源达不到充分的利用
     *
     * https://tomcat.apache.org/tomcat-8.5-doc/config/executor.html
     *
     * http://tomcat.apache.org/tomcat-8.5-doc/config/http.html#Connector_Comparison
     */
    @RequestMapping("/maxThreads")
    public R maxThreads() {
        log.debug("### 当前线程 ###" + Thread.currentThread().getId() + ",name:" + Thread.currentThread().getName());

        // 模拟业务处理时间
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return R.ok();
    }

    @RequestMapping("/minSpareThreads")
    public R minSpareThreads() {

        return R.ok();
    }

}

