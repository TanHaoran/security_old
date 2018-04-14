package com.jerry.security.core.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/14
 * Time: 22:07
 * Description:
 */
@Data
public class QQProperties extends SocialProperties {

    private String providerId = "qq";
}
