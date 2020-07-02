package com.yubo.springboot.mappers;

import com.yubo.springboot.modal.AyUser;
import com.yubo.springboot.modal.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author yubo
 * @version V1.0
 * @description
 * @date 2020/7/2 13:39
 */
@Mapper
public interface MybatisAyUserMapper {

    /**
     * 根据id查找user
     *
     * @param id
     * @return
     */
    User getUserById(@Param("id") String id);
}
