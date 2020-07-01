package com.yubo.springboot.dao;

/**
 * @author yubo
 * @version V1.0
 * @description
 * @date 2020/7/1 11:32
 */
public interface RedisDao {
    void add(String key, Object object);

    Object select(String key);

    void delete(String key);

    void update(String key, Object object);
}
