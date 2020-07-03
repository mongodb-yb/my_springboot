package com.yubo.springboot.message.consumer;

import com.yubo.springboot.modal.UserMood;
import com.yubo.springboot.service.UserMoodService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author yubo
 * @version V1.0
 * @description 微信述说消费者
 * @date 2020/7/3 11:41
 */
@Component
public class UserMoodConsumer {
    /**
     * 配置消費者监听器监听user_mood消息队列
     *
     * @param text
     */
    @JmsListener(destination = "user_mood_queue")
    public void receive(String text) {
        System.out.println("消费者1：" + text);
    }


    @Resource
    private UserMoodService userMoodService;

    @JmsListener(destination = "asyn_user_mood")
    public void receive(UserMood userMood) {
        userMoodService.save(userMood);
        System.out.println("接收到微信说说信息了");
    }
}
