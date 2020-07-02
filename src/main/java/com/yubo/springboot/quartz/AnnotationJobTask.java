package com.yubo.springboot.quartz;

import com.yubo.springboot.modal.AyUser;
import com.yubo.springboot.service.MailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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

    @Resource
    private MailService mailService;

    @Scheduled(cron = "0/1 * * * * ?")
    public void execute() {
        AyUser ayUser = new AyUser();
        ayUser.setId("4");
        try {
            mailService.sendMail(ayUser);
            logger.info("定时任务执行了");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("定时任务执行了");
        }
    }
}
