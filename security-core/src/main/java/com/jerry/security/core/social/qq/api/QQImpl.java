package com.jerry.security.core.social.qq.api;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/14
 * Time: 19:25
 * Description:
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    /**
     * 获取用户openid
     */
    private static final String URL_GET_OPENID = "http://graph.qq.com/oauth2.0/me?access_token=%s";

    /**
     * 获取用户信息
     */
    private static final String URL_GET_USER_INFO = "http://graph.qq.com/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appid;

    private String openid;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);

        this.appid = appId;

        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);

        System.out.println(result);

        this.openid = StringUtils.substringBetween(result, "\"openid\"" + "}");
    }

    @Override
    public QQUserInfo getQQUserInfo() {

        String url = String.format(URL_GET_USER_INFO, appid, openid);

        String result = getRestTemplate().getForObject(url, String.class);

        System.out.println(result);

        try {
            return objectMapper.readValue(result, QQUserInfo.class);
        } catch (IOException e) {
            throw new RuntimeException("获取用户信息失败", e);
        }
    }
}
