package com.jerry.security.core.social.qq.config;

import com.jerry.security.core.properties.QQProperties;
import com.jerry.security.core.properties.SecurityProperties;
import com.jerry.security.core.social.qq.Connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;

import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/14
 * Time: 22:12
 * Description:
 */
@Configuration
@ConditionalOnProperty(prefix = "jerry.security.social.qq", name = "app-id")
@Order(2)
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqProperties = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qqProperties.getProviderId(), qqProperties.getAppId(),
                qqProperties.getAppSecret());
    }
}
