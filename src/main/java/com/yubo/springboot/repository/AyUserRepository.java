package com.yubo.springboot.repository;

import com.yubo.springboot.modal.AyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * @author yubo
 * @version V1.0
 * @description 定义AyUser的JPA
 * JpaRepository<T, ID> T：对应的实体类型  ID：对应的实体的id字段的类型
 * 这里T 是AyUser，ID 是String类型
 * 同时，在AyUser的实体使用注解，与数据库表建立映射关系
 * @date 2020/4/24 14:39
 */
public interface AyUserRepository extends JpaRepository<AyUser, String> {

    // 除了Repository接口实现类封装的方法外，我们也可以自定义方法
    // Repository定义了许多的关键字：findBy、Like、In等等
    // 定义好方法就可以在service层使用了

    List<AyUser> findByName(String name);

    List<AyUser> findByNameLike(String name);

    List<AyUser> findByIdIn(Collection<String> ids);
}
