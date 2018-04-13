package com.jerry.security.core.validate.code;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/11
 * Time: 11:24
 * Description: 校验码生成器接口
 */
@Component
public interface ValidateCodeGenerator {

    /**
     * 根据请求生成校验码
     *
     * @param request
     * @return
     */
    ValidateCode generate(ServletWebRequest request);
}
