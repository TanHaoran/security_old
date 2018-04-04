package com.jerry.web.config;

import com.jerry.web.filter.TimeFilter;
import com.jerry.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/3
 * Time: 10:50
 * Description:
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private TimeInterceptor timeInterceptor;

    // 配置异步支持的
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        // 使用下面这两个方法配置异步拦截器的
        // configurer.registerCallableInterceptors();
        // configurer.registerDeferredResultInterceptors();
        // 配置超时时间
        // configurer.setDefaultTimeout(10000);
        // 设置可重用的线程池来替代Spring默认的线程池
        // configurer.setTaskExecutor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(timeInterceptor);
    }

    //    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);

        List<String> urls = new ArrayList<>();
        // 根据具体的路径进行设置"/*"表示匹配所有的路径
        urls.add("/*");

        registrationBean.setUrlPatterns(urls);
        return registrationBean;
    }
}
