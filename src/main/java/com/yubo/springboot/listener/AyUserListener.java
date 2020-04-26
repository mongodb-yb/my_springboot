package com.yubo.springboot.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

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

    /**
     * servlet容器启动程序时调用该方法，然后再创建Filter拦截器
     * 类似于InitializingBean类的功能
     *
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("监听对象的创建");
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
