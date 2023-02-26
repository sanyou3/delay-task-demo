package com.sanyou.delay.task.demo.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * TODO
 *
 * @author yisuan
 * @date 2023/2/26 21:11
 */
@RestController
@Slf4j
public class RocketMQDelayTaskController {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/rocketmq/add")
    public void addTask(@RequestParam("task") String task) throws Exception {
        DefaultMQProducer producer = rocketMQTemplate.getProducer();
        Message msg = new Message("sanyouDelayTaskTopic", "TagA", task.getBytes(RemotingHelper.DEFAULT_CHARSET));
        msg.setDelayTimeLevel(2);
        // 发送消息并得到消息的发送结果，然后打印
        log.info("提交延迟任务");
        producer.send(msg);
    }

}
