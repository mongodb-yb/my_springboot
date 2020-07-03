package com.yubo.springboot.message.consumer;

import org.springframework.core.annotation.Order;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author yubo
 * @version V1.0
 * @description
 * @date 2020/7/3 13:44
 */
@Component
public class UserMoodConsumer1 {

//    @JmsListener(destination = "user_mood_queue")
    public void receive(String text) {
        System.out.println("消费者2：" + text);
    }
}
