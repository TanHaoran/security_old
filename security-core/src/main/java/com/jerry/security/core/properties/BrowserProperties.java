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
    private String loginPage = "/signIn.html";

    private LoginType loginType = LoginType.JSON;

    private int rememberMeSeconds = 3600;

}
