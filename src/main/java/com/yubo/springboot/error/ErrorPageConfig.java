package com.yubo.springboot.error;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @author yubo
 * @version V1.0
 * @description springboot2.0以上不支持EmbeddedServletContainerCustomizer（嵌入式servlet容器定制）
 * 因此使用 ErrorPageRegistrar
 * @date 2020/7/7 10:15
 */
@Configuration
public class ErrorPageConfig implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
        registry.addErrorPages(error404Page);
    }
}
