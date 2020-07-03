package com.yubo.springboot.message.producer;

import com.yubo.springboot.modal.UserMood;
import com.yubo.springboot.service.UserMoodService;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @author yubo
 * @version V1.0
 * @description 微信说说生产者
 * @date 2020/7/3 11:41
 */
@Service
public class UserMoodProducer {

    /**
     * 发送消息的工具类，也可以使用JmsTemplate，但是JmsMessagingTemplate
     * 对其进行了封装。
     */
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 发送消息
     *
     * @param destination 要发送到的队列
     * @param message     待发送的消息
     */
    public void sendMessage(Destination destination, final String message) {
        jmsMessagingTemplate.convertAndSend(destination, message);
    }


    public void sendMessage(Destination destination, final UserMood userMood) {
        jmsMessagingTemplate.convertAndSend(destination, userMood);
    }
}
