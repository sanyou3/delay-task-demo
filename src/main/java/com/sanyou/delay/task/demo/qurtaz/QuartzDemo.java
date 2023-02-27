package com.sanyou.delay.task.demo.qurtaz;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * 微信公众号：三友的java日记
 *
 * @author sanyou
 * @date 2023/2/27 07:51
 */
@Slf4j
public class QuartzDemo {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        // 1.创建Scheduler的工厂
        SchedulerFactory sf = new StdSchedulerFactory();
        // 2.从工厂中获取调度器实例
        Scheduler scheduler = sf.getScheduler();

        // 6.启动 调度器
        scheduler.start();

        // 3.创建JobDetail
        JobDetail jb = JobBuilder.newJob(SanYouJob.class)
                .usingJobData("delayTask", "这是一个延迟任务")
                .build();

        // 4.创建Trigger
        Trigger t = TriggerBuilder.newTrigger()
                //任务的触发时间就是延迟任务到的延迟时间
                .startAt(DateUtil.offsetSecond(new Date(), 5))
                .build();

        // 5.注册任务和定时器
        log.info("提交延迟任务");
        scheduler.scheduleJob(jb, t);
    }

    @Slf4j
    public static class SanYouJob implements Job {

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            JobDetail jobDetail = context.getJobDetail();
            JobDataMap jobDataMap = jobDetail.getJobDataMap();
            log.info("获取到延迟任务:{}", jobDataMap.get("delayTask"));
        }

    }

}
