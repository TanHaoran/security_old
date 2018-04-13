package com.jerry.security.core.properties;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/11
 * Time: 10:39
 * Description: 短信校验码属性
 */
@Data
public class SmsCodeProperties {

    /**
     * 校验码长度
     */
    private int length = 6;

    /**
     * 校验码过期时间
     */
    private int expireIn = 60;

    /**
     * 校验地址：使用逗号分隔
     */
    private String url;
}
