package com.jerry.security.core.validate.code;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/11
 * Time: 11:24
 * Description:
 */
@Component
public interface ValidateCodeGenerator {

    ImageCode generate(ServletWebRequest request);
}
