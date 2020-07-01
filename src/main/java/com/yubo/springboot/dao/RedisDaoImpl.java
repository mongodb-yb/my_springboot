package com.yubo.springboot.dao;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yubo
 * @version V1.0
 * @description
 * @date 2020/7/1 11:32
 */
@Service
public class RedisDaoImpl implements RedisDao {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void add(String key, Object object) {
        redisTemplate.opsForValue().set(key, object);
    }

    @Override
    public Object select(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void update(String key, Object object) {
        redisTemplate.opsForValue().set(key, object);
    }
}
