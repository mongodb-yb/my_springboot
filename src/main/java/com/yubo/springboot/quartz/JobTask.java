package com.yubo.springboot.quartz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author yubo
 * @version V1.0
 * @description xml配置定时任务
 * @date 2020/7/2 10:11
 */
public class JobTask {
    private static final Logger logger = LogManager.getLogger(JobTask.class);

    public void execute() {
        logger.info("定时任务开始执行了。。。");
    }
}
