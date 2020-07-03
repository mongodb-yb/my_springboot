package com.yubo.springboot.service;

import com.yubo.springboot.modal.UserMood;

/**
 * @author yubo
 * @version V1.0
 * @description
 * @date 2020/7/3 11:11
 */
public interface UserMoodService {

    UserMood save(UserMood userMood);

    /**
     * 异步消费
     *
     * @param userMood
     * @return
     */
    String asynSave(UserMood userMood);
}
