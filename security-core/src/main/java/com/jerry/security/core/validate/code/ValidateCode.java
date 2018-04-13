package com.jerry.security.core.validate.code;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/4
 * Time: 16:56
 * Description: 短信校验码
 */
@Data
public class ValidateCode {

    /**
     * 校验码内容
     */
    private String code;

    /**
     * 校验码内容过期时间
     */
    private LocalDateTime expireTime;

    /**
     * 带有有效秒数的构造方法
     *
     * @param code
     * @param expireIn 有效秒数
     */
    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    /**
     * 校验码是否过
     *
     * @return
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
