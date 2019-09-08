package com.idwxy.fli.config;

import com.idwxy.fli.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 过滤器配置文件
// @Configuration 标识本类为配置类
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<LoginFilter> registFilter() {
        // 创建注册器
        FilterRegistrationBean<LoginFilter> registration = new FilterRegistrationBean<LoginFilter>();
        // 设置过滤器
        registration.setFilter(new LoginFilter());
        // 设置属性 exclusions,不需要过滤的 URL
        registration.addInitParameter("exclusions", "/user/login");
        // 添加过滤规则
        registration.addUrlPatterns("/*");
        // 设置过滤器名称
        registration.setName("LoginFilter");
        // 设置过滤器顺序，值越小优先级越高
        registration.setOrder(1);
        return registration;

    }
}
