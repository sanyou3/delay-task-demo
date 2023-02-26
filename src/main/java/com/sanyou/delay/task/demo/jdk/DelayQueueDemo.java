package com.sanyou.delay.task.demo.jdk;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * TODO
 *
 * @author yisuan
 * @date 2023/2/26 12:57
 */
@Slf4j
public class DelayQueueDemo {

    public static void main(String[] args) {
        DelayQueue<SanYouTask> sanYouTaskDelayQueue = new DelayQueue<>();

        new Thread(() -> {
            while (true) {
                try {
                    SanYouTask sanYouTask = sanYouTaskDelayQueue.take();
                    log.info("获取到延迟任务:{}", sanYouTask.getTaskContent());
                } catch (Exception e) {
                }
            }
        }).start();

        log.info("提交延迟任务");
        sanYouTaskDelayQueue.offer(new SanYouTask("三友的java日记5s", 5L));
        sanYouTaskDelayQueue.offer(new SanYouTask("三友的java日记3s", 3L));
        sanYouTaskDelayQueue.offer(new SanYouTask("三友的java日记8s", 8L));
    }

    @Getter
    @Setter
    public static class SanYouTask implements Delayed {

        private final String taskContent;

        private final Long triggerTime;

        public SanYouTask(String taskContent, Long delayTime) {
            this.taskContent = taskContent;
            this.triggerTime = System.currentTimeMillis() + delayTime * 1000;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(triggerTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return this.triggerTime.compareTo(((SanYouTask) o).triggerTime);
        }

    }

}
