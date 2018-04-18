package com.jerry.security.core.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/18
 * Time: 10:26
 * Description:
 */
@Data
public class WeixinProperties extends SocialProperties {

    /**
     * 第三方id，用来决定发起第三方登录的url，默认是weixin。
     */
    private String providerId = "weixin";

}
