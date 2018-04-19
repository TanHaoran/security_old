package com.jerry.security.browser;

import com.jerry.security.browser.session.MyExpiredSessionStrategy;
import com.jerry.security.core.authentication.AbstractChannelSecurityConfig;
import com.jerry.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.jerry.security.core.properties.SecurityConstants;
import com.jerry.security.core.properties.SecurityProperties;
import com.jerry.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/4
 * Time: 9:11
 * Description: 配置主类
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 校验码过滤器配置
     */
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    /**
     * 自定义的短信校验码认证配置
     */
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private SpringSocialConfigurer securitySocialConfigurer;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;


    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 设置程序启动时自动创建表，这个方法只需第一次启动执行，后面注释即可
        // tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);

        // 用表单登录，对所有的请求都需要做身份认证
        // 在UsernamePasswordAuthenticationFilter前面加入我们自定义的校验码过滤器
        http.apply(validateCodeSecurityConfig)
                .and()

                .apply(smsCodeAuthenticationSecurityConfig)
                .and()

                .apply(securitySocialConfigurer)
                .and()

                .rememberMe()
                // 设置token获取类
                .tokenRepository(persistentTokenRepository())
                // 设置过期时间
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                // 设置验证规则
                .userDetailsService(userDetailsService)
                .and()

                .sessionManagement()
                .invalidSessionStrategy(invalidSessionStrategy)
                .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
                .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
                .expiredSessionStrategy(sessionInformationExpiredStrategy)
                .and()

                .and()

                .logout()
                // 退出的URL
                // .logoutUrl("/signOut")
                // 退出成功后跳转页面
                // .logoutSuccessUrl("/logout.html")
                .logoutSuccessHandler(logoutSuccessHandler)
                // 也可以指定清除某个Cookies
                .deleteCookies("JSESSIONID")
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
