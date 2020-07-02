package com.yubo.springboot.service;

import com.yubo.springboot.modal.AyUser;
import com.yubo.springboot.modal.User;

/**
 * @author yubo
 * @version V1.0
 * @description
 * @date 2020/7/2 13:37
 */
public interface MybatisAyUserService {

    User getUserById(String id);
}
