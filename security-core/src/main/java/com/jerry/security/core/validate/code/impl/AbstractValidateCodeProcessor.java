package com.jerry.security.core.validate.code.impl;

import com.jerry.security.core.validate.code.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/12
 * Time: 0:30
 * Description: 校验码处理器抽象类，需要一个ValidateCode的子类作为泛型参数，实现了校验码处理器接口。
 */
public abstract class AbstractValidateCodeProcessor<T extends ValidateCode> implements ValidateCodeProcessor {

    /**
     * 操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现。
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    /**
     * 做三个操作：生成、保存、发送
     *
     * @param request
     * @throws Exception
     */
    @Override
    public void create(ServletWebRequest request) throws Exception {
        // 生成校验码
        T validateCode = generate(request);
        // 保存校验码
        save(request, validateCode);
        // 发送校验码
        send(request, validateCode);
    }

    /**
     * 生成校验码
     *
     * @param request
     * @return
     */
    private T generate(ServletWebRequest request) {
        String type = getValidateCodeType().toString().toLowerCase();
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(type + "CodeGenerator");
        return (T) validateCodeGenerator.generate(request);
    }

    /**
     * 保存校验码到session中
     *
     * @param request
     * @param validateCode
     */
    private void save(ServletWebRequest request, T validateCode) {
        // 取出验证码内容和过期时间，避免了放置图像到Redis而报错
        ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
        sessionStrategy.setAttribute(request, getSessionKey(), code);
    }

    /**
     * 发送校验码，由子类实现
     *
     * @param request
     * @param validateCode
     * @throws Exception
     */
    protected abstract void send(ServletWebRequest request, T validateCode) throws Exception;

    /**
     * 根据当前的校验码处理器获取校验码的类型
     *
     * @return
     */
    private ValidateCodeType getValidateCodeType() {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    /**
     * 构建验证码放入session时的key
     *
     * @return
     */
    private String getSessionKey() {
        return SESSION_KEY_PREFIX + getValidateCodeType().toString().toUpperCase();
    }

    /**
     * 校验校验码
     *
     * @param request
     */
    @Override
    public void validate(ServletWebRequest request) {

        ValidateCodeType processorType = getValidateCodeType();
        String sessionKey = getSessionKey();

        T codeInSession = (T) sessionStrategy.getAttribute(request, sessionKey);

        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
                    processorType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取校验码的值失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException(processorType + "校验码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException(processorType + "校验码不存在");
        }

        if (codeInSession.isExpired()) {
            sessionStrategy.removeAttribute(request, sessionKey);
            throw new ValidateCodeException(processorType + "校验码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException(processorType + "校验码不匹配");
        }

        sessionStrategy.removeAttribute(request, sessionKey);
    }
}
