package com.sanyou.delay.task.demo.jdk;

import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author yisuan
 * @date 2023/2/26 16:40
 */
@Slf4j
public class ScheduledThreadPoolExecutorDemo {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2, new ThreadPoolExecutor.CallerRunsPolicy());

        log.info("提交延迟任务");
        executor.schedule(() -> log.info("执行延迟任务"), 5, TimeUnit.SECONDS);
    }

}
