package com.jerry.security.browser.support;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/17
 * Time: 20:02
 * Description:
 */
@Data
public class SocialUserInfo {

    /**
     * 表明是哪个第三方应用
     */
    private String providerId;

    /**
     * 用户的openid
     */
    private String providerUserId;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String headImg;
}
