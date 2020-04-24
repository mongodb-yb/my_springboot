package com.yubo.springboot.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yubo
 * @version V1.0
 * @description druid连接池的配置类，注入spring容器中
 * @date 2020/4/24 10:49
 */
/*spring中有很多配置文件
 * 该注解标明该类等价于一个配置文件
 * */
@Configuration
public class DruidConfiguration {

    /*注册Servlet和Filter类*/

    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        // 进行注册
        ServletRegistrationBean servletRegistrationBean =
                new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // 添加初始化参数：initParam
        // 白名单：
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        // 黑名单：
        // 黑白名单的ip一样时，deny优于allow
        // ip满足deny，会提示：sorry，you are not permitted to view this page
        servletRegistrationBean.addInitParameter("deny", "192.168.3.108");
        // 登录查看的账号和密码
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        // 是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        // 添加需要忽略的格式信息
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
    /*@Bean的作用：将该方法的返回值注入到Spring的IOC容器中，即Spring配置文件中的：<bean id=>,方法名为<bean id=方法名>
     * 等价于：例如：FilterRegistrationBean
     *          <bean id="druidStatFilter" class="org.springframework.boot.web.servlet.FilterRegistrationBean">
     * */

    /*配置完成后，重启动项目，访问http://localhost:8090/druid/index.html*/
}
