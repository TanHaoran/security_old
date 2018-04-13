package com.jerry.security.core.properties;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/11
 * Time: 10:39
 * Description: 图形校验码属性
 */
@Data
public class ImageCodeProperties extends SmsCodeProperties {

    /**
     * 宽度
     */
    private int width = 67;

    /**
     * 高度
     */
    private int height = 23;

    public ImageCodeProperties() {
        setLength(4);
    }
}
