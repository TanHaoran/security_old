package com.jerry.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/12
 * Time: 0:30
 * Description: 校验码处理器工具类
 */
@Component
public class ValidateCodeProcessorHolder {

    /**
     * 收集系统中所有的 {@link ValidateCodeProcessor} 接口的实现。
     */
    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    /**
     * 根据校验码类型获取对应的校验码处理器
     *
     * @param type
     * @return
     */
    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
        return findValidateCodeProcessor(type.toString().toLowerCase());
    }

    /**
     * 根绝字符类型获取对应的校验码处理器
     *
     * @param type
     * @return
     */
    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor processor = validateCodeProcessors.get(name);
        if (processor == null) {
            throw new ValidateCodeException("验证码处理器" + name + "不存在");
        }
        return processor;
    }
}