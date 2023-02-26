package com.sanyou.delay.task.demo.redis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author yisuan
 * @date 2023/2/12 18:14
 */
@RestController
public class RedissonDelayQueueController {

    @Resource
    private RedissonDelayQueue redissonDelayQueue;

    @GetMapping("/add")
    public void addTask(@RequestParam("task") String task) {
        redissonDelayQueue.offerTask(task, 5);
    }

}
