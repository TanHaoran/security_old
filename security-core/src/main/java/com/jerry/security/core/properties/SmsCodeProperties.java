package com.jerry.security.core.properties;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/11
 * Time: 10:39
 * Description:
 */
@Data
public class SmsCodeProperties {

    private int length = 6;
    private int expireIn = 60;

    private String url;
}
