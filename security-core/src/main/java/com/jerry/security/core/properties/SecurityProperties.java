package com.jerry.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/4
 * Time: 10:38
 * Description: Security属性配置类
 */
@ConfigurationProperties(prefix = "jerry.security")
@Data
public class SecurityProperties {

    /**
     * 浏览器相关属性
     */
    private BrowserProperties browser = new BrowserProperties();

    /**
     * 校验码相关属性
     */
    private ValidateCodeProperties code = new ValidateCodeProperties();
}
