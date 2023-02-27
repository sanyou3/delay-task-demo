package com.sanyou.delay.task.demo.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 微信公众号：三友的java日记
 *
 * @author sanyou
 * @date 2022/8/14 19:29
 */
@Component
@RocketMQMessageListener(consumerGroup = "sanyouConsumer", topic = "sanyouDelayTaskTopic")
@Slf4j
public class SanYouDelayTaskTopicListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String msg) {
        log.info("获取到延迟任务:{}", msg);
    }

}
