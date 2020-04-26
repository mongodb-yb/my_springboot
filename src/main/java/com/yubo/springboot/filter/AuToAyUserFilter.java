package com.yubo.springboot.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author yubo
 * @version V1.0
 * @description 注解自动注册filter拦截器到容器中
 * @date 2020/4/26 15:14
 */
// 该注解将一个类声明为一个过滤器类
// filterName：过滤器名字，等价于xml中的<filter-name>；
// url-pattern：过滤器的url匹配模式，等价于xml的<url-pattern>
// value等价于url-pattern，但是两者不能同时使用
// 开发完拦截器类后，需要将其注入到ioc容器中：在启动类中使用@ServletComponentScan注解
@WebFilter(filterName = "ayUserFilter", urlPatterns = "/*")
public class AuToAyUserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("调用init初始化Filter======");
    }

    @Override
    public void destroy() {
        System.out.println("调用destroy销毁Filter======");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("执行过滤器");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("过滤器执行完毕");
    }
}
