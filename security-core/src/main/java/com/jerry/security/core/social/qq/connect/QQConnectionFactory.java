package com.jerry.security.core.social.qq.connect;

import com.jerry.security.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/14
 * Time: 21:31
 * Description:
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}
