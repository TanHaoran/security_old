package com.jerry.security.core.properties;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/11
 * Time: 10:41
 * Description: 校验码相关属性
 */
@Data
public class ValidateCodeProperties {

    /**
     * 图形校验码属性
     */
    private ImageCodeProperties image = new ImageCodeProperties();

    /**
     * 短信校验码属性
     */
    private SmsCodeProperties sms = new SmsCodeProperties();
}
