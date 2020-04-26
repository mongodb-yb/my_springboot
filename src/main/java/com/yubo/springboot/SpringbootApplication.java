package com.yubo.springboot;

import com.yubo.springboot.filter.JavaDemoAyUserFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
// 使用该注解表示自动注册，无需任何代码
// Servlet直接通过@WebServlet自动注册到容器
// Filter直接通过@WebFilter自动注册到容器
// Listener直接通过@WebListener自动注册到容器
//@ServletComponentScan
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

}
