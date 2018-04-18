package com.jerry.security.core.social.weixin.api;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/18
 * Time: 10:34
 * Description:
 */
public interface Weixin {

    WeixinUserInfo getUserInfo(String openId);
}
