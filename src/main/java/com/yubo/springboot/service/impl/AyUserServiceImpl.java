package com.yubo.springboot.service.impl;

import com.yubo.springboot.error.BusinessException;
import com.yubo.springboot.modal.AyUser;
import com.yubo.springboot.modal.exception.ControllerException;
import com.yubo.springboot.repository.AyUserRepository;
import com.yubo.springboot.service.AyUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author yubo
 * @version V1.0
 * @description ayUserService的实现类
 * @date 2020/4/24 14:53
 */

/**
 * springBoot会自动扫描@Component注解的类，将其纳入IOC容器。
 * 可以使用@Component注解，但是@Service更能表名该类的性质是服务层类.
 *
 * @Component注解泛指组件，当组件不好归类时可以使用该注解。
 * @Repository注解指DAO组件，即数据访问组件
 *
 * 事务
 * 在service层开启注解：在类上使用，表示该类的所有public方法都开启了事务，即类级事务。
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class AyUserServiceImpl implements AyUserService {

    private final Logger logger = LogManager.getLogger(AyUserServiceImpl.class);

    /**
     * @Resource注解：该注解是J2EE的注解，默认使用名称进行装配。当找不到名称匹配的Bean时，才按照类型进行匹配。
     * @Autowired注解：该注解是spring的注解，默认使用类型装配。默认情况下bean对象必须存在，如果允许为null，可以设置required=false属性值 但是该注解一般会存在歧义性：spring面向接口编程，一个接口会有多个实现类，如果以接口的类型去装配，则会无法找到使用哪个类型。
     * 解决方式1：@Primary注解：可以优先指定装配哪个Bean，只能使用在一个类上，解决了首要性，没有解决选择性
     * 解决方式2：@Qualifier("名字")注解：指定不同的名称进行查找，消除了歧义性，解决了首要性和选择性
     */
    @Resource
    private AyUserRepository ayUserRepository;

    /**
     * @Override注解的作用：帮助校验接口方法是否被修改
     */
    @Override
    public AyUser findById(String id) {
        return ayUserRepository.findById(id).get();
    }

    @Override
    public List<AyUser> findAll() {
        // 测同步调用耗时情况
        logger.info("开始做任务");
        long start = System.currentTimeMillis();
        List<AyUser> list = ayUserRepository.findAll();
        long end = System.currentTimeMillis();
        logger.info("完成任务，耗时：" + (end - start) + "毫秒");
        return list;
    }

    @Override
    // 开启异步调用注解
    @Async
    public Future<List<AyUser>> findAsyncUserAll() {
        // 测异步调用耗时情况
        logger.info("开始做任务");
        long start = System.currentTimeMillis();
        List<AyUser> list = ayUserRepository.findAll();
        long end = System.currentTimeMillis();
        logger.info("完成任务，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<List<AyUser>>(list);
    }


    /**
     * 事务测试
     * 方法级别的事务会覆盖类级别的事务。
     *
     * @param ayUser
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AyUser save(AyUser ayUser) {
        AyUser ayUser1 = ayUserRepository.save(ayUser);
        String testTransactional = null;
        testTransactional.split("l");

        return ayUser1;
    }

    @Override
    public void delete(AyUser ayUser) {
        ayUserRepository.delete(ayUser);
        logger.info("删除用户：" + ayUser.getName() + "的信息");
    }

    /**
     * Pageable是一个分页接口，查询是传入一个该接口的实现类，并指定pageNumber第几页，pageSize每页多少
     * Page：分页查询的结果会保存在Page实体中。Page继承了Slice接口，通过getTotalPages和getContent方法可以获得查询总页数和查询的记录
     */
    @Override
    public Page<AyUser> findAll(Pageable pageable) {
        return ayUserRepository.findAll(pageable);
    }

    // 下面是4个自定义方法：添加完以后就可以测试了。

    /**
     * 模拟抛出BusinessException，开启重试机制
     * value表示出现何种异常时触发重试；
     * maxAttempts表示最大重试次数；
     * delay表示延迟时间；
     * multiplier表示下次重试时间间隔是上次重试时间间隔的n倍
     *
     * @param name 姓名
     * @return
     */
    @Override
    @Retryable(value = {BusinessException.class}, maxAttempts = 5, backoff = @Backoff(delay = 5000, multiplier = 2))
    public List<AyUser> findByName(String name) throws Exception {
        System.out.println("findByName" + "方法重试失败了");
        throw new BusinessException(ControllerException.SYSTEM_ERROR);
    }

    @Override
    public List<AyUser> findByNameLike(String name) {
        return ayUserRepository.findByNameLike(name);
    }

    @Override
    public List<AyUser> findByIdIn(Collection<String> ids) {
        return ayUserRepository.findByIdIn(ids);
    }
}
