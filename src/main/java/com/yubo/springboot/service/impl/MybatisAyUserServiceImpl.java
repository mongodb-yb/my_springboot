package com.yubo.springboot.service.impl;

import com.yubo.springboot.mappers.MybatisAyUserMapper;
import com.yubo.springboot.modal.AyUser;
import com.yubo.springboot.modal.User;
import com.yubo.springboot.service.MybatisAyUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yubo
 * @version V1.0
 * @description
 * @date 2020/7/2 13:38
 */
@Service
public class MybatisAyUserServiceImpl implements MybatisAyUserService {

    @Resource
    private MybatisAyUserMapper mybatisAyUserMapper;

    @Override
    public User getUserById(String id) {
        return mybatisAyUserMapper.getUserById(id);
    }
}
