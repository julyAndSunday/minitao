package com.minitao.common.config;

import com.minitao.common.resolver.AuthenticationInterceptor;
import com.minitao.common.resolver.UserMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @Description:
 * @Author: July
 * @Date: 2021-11-13 22:21
 **/
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Autowired
    UserMethodArgumentResolver userMethodArgumentResolver;

    @Autowired
    AuthenticationInterceptor authenticationInterceptor;

    /**
     * 参数解析器
     *
     * @param argumentResolvers
     */
    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userMethodArgumentResolver);
        super.addArgumentResolvers(argumentResolvers);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}

