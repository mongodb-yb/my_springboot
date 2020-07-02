package com.yubo.springboot.quartz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author yubo
 * @version V1.0
 * @description 注解实现定时任务
 * @date 2020/7/2 10:45
 */
@Component
@Configurable
@EnableScheduling
public class AnnotationJobTask {
    private static final Logger logger = LogManager.getLogger(AnnotationJobTask.class);

    @Scheduled(cron = "0/10 * * * * ?")
    public void execute() {
        logger.info("注解配置的定时任务开始执行了。。。");
    }
}
