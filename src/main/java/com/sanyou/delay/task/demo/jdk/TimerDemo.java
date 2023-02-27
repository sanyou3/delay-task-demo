package com.sanyou.delay.task.demo.jdk;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 微信公众号：三友的java日记
 *
 * @author sanyou
 * @date 2023/2/26 15:37
 */
@Slf4j
public class TimerDemo {

    public static void main(String[] args) {
        Timer timer = new Timer();

        log.info("提交延迟任务");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("执行延迟任务");
            }
        }, 5000);
    }

}
