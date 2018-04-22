package com.jerry.security.app;

import com.jerry.security.app.authentication.openid.OpenIdAuthenticationSecurityConfig;
import com.jerry.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.jerry.security.core.properties.SecurityConstants;
import com.jerry.security.core.properties.SecurityProperties;
import com.jerry.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/19
 * Time: 14:59
 * Description:
 */
@Configuration
@EnableResourceServer
public class MyResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    protected AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private SpringSocialConfigurer securitySocialConfigurer;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Autowired
    private SecurityProperties securityProperties;


    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                // 自定义登陆页面
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                // 自定义登录处理页面
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler);

        // 用表单登录，对所有的请求都需要做身份认证
        // 在UsernamePasswordAuthenticationFilter前面加入我们自定义的校验码过滤器
        http.apply(validateCodeSecurityConfig)
                .and()

                .apply(smsCodeAuthenticationSecurityConfig)
                .and()

                .apply(securitySocialConfigurer)
                .and()

                .apply(openIdAuthenticationSecurityConfig)
                .and()

                // 对请求做授权
                .authorizeRequests()
                // 不用登录验证的链接
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                        securityProperties.getBrowser().getSignUpUrl(),
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl() + ".json",
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl() + ".html",
                        securityProperties.getBrowser().getSignOutUrl(),
                        "/user/register"
                ).permitAll()
                // 任何请求
                .anyRequest()
                // 都需要身份认证
                .authenticated()
                // 关闭跨站请求伪造防护
                .and().csrf().disable();
    }
}
