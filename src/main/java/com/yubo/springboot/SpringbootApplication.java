package com.yubo.springboot;

import com.yubo.springboot.filter.JavaDemoAyUserFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@SpringBootApplication
// 使用该注解表示自动注册，无需任何代码
// Servlet直接通过@WebServlet自动注册到容器
// Filter直接通过@WebFilter自动注册到容器
// Listener直接通过@WebListener自动注册到容器
@ServletComponentScan
// spring-boot将mvc配置文件扫描
//@ImportResource(locations = {"classpath:spring-mvc.xml"})
// 开启异步调用
@EnableAsync
// 开启重试机制
@EnableRetry
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    /**
     * java代码方式注册拦截器到容器中
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean registrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setFilter(new JavaDemoAyUserFilter());
        return filterRegistrationBean;
    }

    /**
     * java代码方式注册监听器到容器中
     *
     * @return
     */
//    @Bean
    public ServletContextListener getListener() {
        ServletContextListener servletContextListener = new ServletContextListener() {
            @Override
            public void contextInitialized(ServletContextEvent sce) {
                System.out.println("哈哈，监听对象的创建");
            }

            @Override
            public void contextDestroyed(ServletContextEvent sce) {
                System.out.println("监听对象的销毁");
            }
        };

        return servletContextListener;
    }
}
