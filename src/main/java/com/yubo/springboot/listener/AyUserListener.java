package com.yubo.springboot.listener;

import com.google.gson.Gson;
import com.yubo.springboot.modal.AyUser;
import com.yubo.springboot.service.AyUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * @author yubo
 * @version V1.0
 * @description 监听器
 * @date 2020/4/26 16:21
 */
// 该注解将一个类声明为监听器类
// ServletContextListener 能够监听ServletContext的生命周期
@WebListener
public class AyUserListener implements ServletContextListener {

    private static final Logger logger = LogManager.getLogger(AyUserListener.class);

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private AyUserService ayUserService;

    private final String ALL_USER_KEY = "all_user_list";
    private final Gson gson = new Gson();

    /**
     * servlet容器启动程序时调用该方法，然后再创建Filter拦截器
     * 类似于InitializingBean类的功能
     *
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("将所有用户信息保存到redis中");
        // 项目初始化时加载所有用户信息
        List<AyUser> list = ayUserService.findAll();
        logger.info("用户数量为：" + list.size());
        // 将所有用户数据保存到redis中
        redisTemplate.opsForList().leftPushAll(ALL_USER_KEY, list);
        List<AyUser> redisList = redisTemplate.opsForList().range(ALL_USER_KEY, 0, -1);
        logger.info("redis用户数量为：" + redisList.size());
//        redisList.stream().forEach(item -> System.out.println(gson.toJson(item)));
    }

    /**
     * servlet容器终止程序时调用该方法，调用之前会销毁所有servlet和filter资源
     *
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
