package com.sanyou.delay.task.demo.hashwheel;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author yisuan
 * @date 2023/2/26 18:34
 */
@Slf4j
public class NettyHashedWheelTimerDemo {

    public static void main(String[] args) {
//        HashedWheelTimer timer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS, 8);
//        timer.start();
//
//        log.info("提交延迟任务");
//        timer.newTimeout(timeout -> log.info("执行延迟任务"), 5, TimeUnit.SECONDS);


        System.out.println(8 & 7);
    }

}
