package com.yubo.springboot.service;

import com.yubo.springboot.modal.AyUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author yubo
 * @version V1.0
 * @description ayUser的服务层 ： 定义几个方法
 * @date 2020/4/24 14:51
 */
public interface AyUserService {

    AyUser findById(String id);

    List<AyUser> findAll();

    /**
     * 异步调用获取全部用户
     *
     * @return
     */
    Future<List<AyUser>> findAsyncUserAll();

    AyUser save(AyUser ayUser);

    void delete(AyUser ayUser);

    /**
     * 分页查询
     *
     * @param pageable
     * @return
     */
    Page<AyUser> findAll(Pageable pageable);

    List<AyUser> findByName(String name) throws Exception;

    List<AyUser> findByNameLike(String name);

    List<AyUser> findByIdIn(Collection<String> ids);
}
