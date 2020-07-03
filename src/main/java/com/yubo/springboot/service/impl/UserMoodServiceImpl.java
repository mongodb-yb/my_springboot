package com.yubo.springboot.service.impl;

import com.yubo.springboot.message.producer.UserMoodProducer;
import com.yubo.springboot.modal.UserMood;
import com.yubo.springboot.repository.UserMoodRepository;
import com.yubo.springboot.service.UserMoodService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @author yubo
 * @version V1.0
 * @description
 * @date 2020/7/3 11:12
 */
@Service
public class UserMoodServiceImpl implements UserMoodService {

    @Resource
    private UserMoodRepository userMoodRepository;

    @Override
    public UserMood save(UserMood userMood) {
        return userMoodRepository.save(userMood);
    }


    private static final Destination destination = new ActiveMQQueue("asyn_user_mood");
    @Resource
    private UserMoodProducer userMoodProducer;

    @Override
    public String asynSave(UserMood userMood) {
        userMoodProducer.sendMessage(destination, userMood);
        return "success";
    }
}
