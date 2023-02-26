package com.sanyou.delay.task.demo.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * TODO
 *
 * @author yisuan
 * @date 2023/2/26 22:23
 */
@RestController
@Slf4j
public class RabbitMQDelayTaskController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/rabbitmq/add")
    public void addTask(@RequestParam("task") String task) throws Exception {
        // 消息ID，需要封装到CorrelationData中
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        log.info("提交延迟任务");
        // 发送消息
        rabbitTemplate.convertAndSend("sanyouDirectExchangee", "", task, correlationData);
    }

}
