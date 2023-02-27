package com.sanyou.delay.task.demo.polling;

import com.sanyou.delay.task.demo.jdk.DelayQueueDemo;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 微信公众号：三友的java日记
 *
 * @author sanyou
 * @date 2023/2/27 07:34
 */
@Slf4j
public class PollingTaskDemo {

    private static final List<DelayTask> DELAY_TASK_LIST = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                try {
                    for (DelayTask delayTask : DELAY_TASK_LIST) {
                        if (delayTask.triggerTime <= System.currentTimeMillis()) {
                            log.info("处理延迟任务:{}", delayTask.taskContent);
                            DELAY_TASK_LIST.remove(delayTask);
                        }
                    }
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (Exception e) {
                }
            }
        }).start();

        log.info("提交延迟任务");
        DELAY_TASK_LIST.add(new DelayTask("三友的java日记", 5L));
    }

    @Getter
    @Setter
    public static class DelayTask {

        private final String taskContent;

        private final Long triggerTime;

        public DelayTask(String taskContent, Long delayTime) {
            this.taskContent = taskContent;
            this.triggerTime = System.currentTimeMillis() + delayTime * 1000;
        }
    }

}
