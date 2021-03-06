package com.jerry.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/3
 * Time: 10:39
 * Description: 过滤器，这会对所有的请求进行过滤，这只能收到请求，并不能收到请求中的数据、方法等等
 */
// 使用Component注解，可以直接将过滤器添加到组件中进行过滤，如果使用第三方的过滤器没有Component注解的话，那么需要做配置文件
// @Component
public class TimeFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("【过滤器】初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("【过滤器】开始");
        long start = new Date().getTime();
        chain.doFilter(request, response);
        System.out.println("【过滤器】耗时: " + (new Date().getTime() - start));
        System.out.println("【过滤器】结束");
    }

    @Override
    public void destroy() {
        System.out.println("【过滤器】销毁");
    }

}
