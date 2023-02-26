package com.sanyou.delay.task.demo.mq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author yisuan
 * @date 2023/2/26 22:25
 */
@Configuration
public class RabbitMQConfiguration {

    @Bean
    public DirectExchange sanyouDirectExchangee() {
        return new DirectExchange("sanyouDirectExchangee");
    }

    @Bean
    public Queue sanyouQueue() {
        return QueueBuilder
                //指定队列名称，并持久化
                .durable("sanyouQueue")
                //设置队列的超时时间为5秒，也就是延迟任务的时间
                .ttl(5000)
                //指定死信交换机
                .deadLetterExchange("sanyouDelayTaskExchangee")
                .build();
    }

    @Bean
    public Binding sanyouQueueBinding() {
        return BindingBuilder.bind(sanyouQueue()).to(sanyouDirectExchangee()).with("");
    }

    @Bean
    public DirectExchange sanyouDelayTaskExchange() {
        return new DirectExchange("sanyouDelayTaskExchangee");
    }

    @Bean
    public Queue sanyouDelayTaskQueue() {
        return QueueBuilder
                //指定队列名称，并持久化
                .durable("sanyouDelayTaskQueue")
                .build();
    }

    @Bean
    public Binding sanyouDelayTaskQueueBinding() {
        return BindingBuilder.bind(sanyouDelayTaskQueue()).to(sanyouDelayTaskExchange()).with("");
    }

}
