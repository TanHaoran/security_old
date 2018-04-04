package com.jerry.security.browser;

import com.jerry.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/4
 * Time: 9:11
 * Description:
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 用表单登录，对所有的请求都需要做身份认证
        http.formLogin()
                // 自定义登录页面
                .loginPage("/authentication/require")
                // 自定义表单提交路径
                .loginProcessingUrl("/authentication/form")
                // 使用and连接不同的校验项
                .and()
                .authorizeRequests()
                // 表示匹配到下面这个页面的时候不需要身份认证，其他页面需要身份认证
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage()).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                // 禁用跨扎请求伪造防护功能
                .csrf().disable();

        // 使用httpBasic登录使用下面这个
        // http.httpBasic()
    }


}