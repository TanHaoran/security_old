package com.jerry.security.core.properties;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/4
 * Time: 10:38
 * Description:
 */
@Data
public class BrowserProperties {

    /**
     * 默认的认证页面
     */
    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    /**
     * 默认的注册页面
     */
    private String signUpUrl = SecurityConstants.DEFAULT_SIGN_UP_URL;


    /**
     * 默认的请求返回类型
     */
    private LoginType loginType = LoginType.JSON;

    /**
     * 记住我的时间
     */
    private int rememberMeSeconds = 3600;

}
